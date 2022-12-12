package Controller;
import Model.Ventas.AlertaVenta;
import Model.Ventas.FacturaReporte;
import Model.Ventas.TablaGestionFactura;
import Model.Ventas.TablaPersona;
import Model.ViewFuntionality;
import Reportes.ReporteFactura;
import Services.Ventas.ServiceGestionFactura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;

public class FacturaController extends ViewFuntionality implements Initializable {
    private final ReporteFactura reporteFactura = new ReporteFactura();
    private HomeVentasController homeVentasController;
    private ServiceGestionFactura serviceGestionFactura = new ServiceGestionFactura();
    private FacturaReporte factura;

    @FXML private TableView<TablaGestionFactura> tablaProductos = new TableView();
    @FXML private TableColumn<TablaGestionFactura, String> colNumFactura = new TableColumn<>();
    @FXML private TableColumn<TablaGestionFactura, String> colCliente = new TableColumn<>();
    @FXML private TableColumn<TablaGestionFactura, String> colFecha = new TableColumn<>();
    @FXML private TableColumn<TablaGestionFactura, Double> colTotal = new TableColumn<>();
    @FXML private TextField txtNombreCliente = new TextField();
    @FXML private Button btnFactura;
    @FXML private Button btnCobrarFactura;

    @FXML private ChoiceBox<String> choiceBoxFacturada = new ChoiceBox<>();
    @FXML private Label labelFactura;
    @FXML private Image imageLabel;
    @FXML private ImageView imageView;
    private ObservableList<String> busquedaPorNombre;
    private HashSet<TablaGestionFactura> facturas = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        busquedaPorNombre = FXCollections.observableArrayList();
        iniciarTabla();
        iniciarChoiceBox();
        darEventoChoiceBox();
    }

    private void iniciarTabla() {
        colNumFactura.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        actualizarDatosTabla(true);
    }
    private void iniciarChoiceBox(){
        ObservableList<String> facturado = FXCollections.observableArrayList("FACTURAS COBRADAS", "FACTURAS NO COBRADAS");
        getChoiceBoxFacturada().setItems(facturado);
    }

    private void actualizarDatosTabla(boolean facturada){
        ObservableList<TablaGestionFactura> listado = FXCollections.observableArrayList(serviceGestionFactura.listarFacturas(facturada));
        getTablaProductos().setItems(listado);
    }
    @FXML
    public void accionBuscarPorNombre(KeyEvent event){
        ObservableList<TablaGestionFactura> listadoFacturas = getTablaProductos().getItems();
        try{
            String filtroCodigo = getTxtNombreCliente().getText();
            if (filtroCodigo.isEmpty()){
                String facturada = getChoiceBoxFacturada().getSelectionModel().getSelectedItem();
                if (facturada.equals("FACTURAS COBRADAS")) {
                    actualizarDatosTabla(true);
                }else{
                    actualizarDatosTabla(false);
                }
                facturas.clear();
            }else{
                facturas.clear();
                listadoFacturas.forEach(factura ->
                        {
                            String nombre = String.valueOf(factura.getCliente()).toLowerCase();
                            if (nombre.contains(filtroCodigo.toLowerCase())){
                                facturas.add(factura);
                                listadoFacturas.setAll(facturas);
                            }
                        }
                );
                getTablaProductos().setItems(listadoFacturas);
            }
        }catch (Exception exception){ System.out.println(exception.getMessage()); }
    }
    @FXML
    private void darEventoChoiceBox(){
        boolean isCobrada = getChoiceBoxFacturada().getSelectionModel().getSelectedIndex() == 0;
        if (isCobrada){
            actualizarDatosTabla(true);
            cambiarLabel("FACTURAS COBRADAS");
            cambiarImagenLabel("/Images/check.png");
            getBtnCobrarFactura().setVisible(false);
            colFecha.setText("Fecha de cobro");
        }else{
            actualizarDatosTabla(false);
            cambiarLabel("FACTURAS NO COBRADAS");
            cambiarImagenLabel("/Images/cancelar.png");
            getBtnCobrarFactura().setVisible(true);
            colFecha.setText("Fecha de emision");
        }
    }
    @FXML
    public void accionVerFactura(){
        //Abrir venta factura
        TablaGestionFactura seleccionada = obtenerFactura();
        try {
            setFactura(serviceGestionFactura.obtenerFactura(seleccionada.getNumeroFactura()));
            reporteFactura.loadFactura(   getFactura().getTotalBruto()
                                        , getFactura().getAlicuota()
                                        , getFactura().getTotalNeto()
                                        , getFactura().getNumero()
            );
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void accionCobrarFactura(){
        TablaGestionFactura factura = obtenerFactura();
        if (factura != null){
            getBtnFactura().setDisable(true);
            try {
                serviceGestionFactura.cobrarFactura(factura);
                getBtnFactura().setDisable(false);
                getChoiceBoxFacturada().getSelectionModel().select(0);
                AlertaVenta.facturaCobradaCorrectamente();
            }catch (SQLException e){ AlertaVenta.errorAlCobrarFactura(); }
        }else{ AlertaVenta.seleccioneFactura(); }
    }
    private void cambiarLabel(String texto) { getLabelFactura().setText(texto); }
    private void cambiarImagenLabel(String url) {
        setImageLabel(new Image(getClass().getResourceAsStream(url)));
        getImageView().setImage(getImageLabel());
    }
    private TablaGestionFactura obtenerFactura(){
        try {
            int indice = getTablaProductos().getSelectionModel().getSelectedIndex();
            TablaGestionFactura factura = getTablaProductos().getItems().get(indice);
            return factura;
        }catch (IndexOutOfBoundsException e){
            if (getTablaProductos().getItems().isEmpty()){
                AlertaVenta.noHayFacturas();
            }else{
               /* AlertaVenta.seleccioneFactura()*/;
            }
            return null;
        }
    }

    public void hideStage(){ getVentana().hide(); }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(homeVentasController(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeVentasController().setVentana(homeStage);
        getHomeVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    private HomeVentasController homeVentasController(HomeVentasController controller){ return controller; }
    public HomeVentasController getHomeVentasController() { return homeVentasController; }
    public void setHomeVentasController(HomeVentasController homeVentasController) { this.homeVentasController = homeVentasController; }
    public FacturaReporte getFactura() { return this.factura; }
    public void setFactura(FacturaReporte factura) { this.factura = factura; }
    public TableView<TablaGestionFactura> getTablaProductos() { return tablaProductos; }
    public TextField getTxtNombreCliente() { return txtNombreCliente; }
    public ObservableList<String> getBusquedaPorNombre() { return busquedaPorNombre; }
    public void setBusquedaPorNombre(ObservableList<String> busquedaPorNombre) { this.busquedaPorNombre = busquedaPorNombre; }
    public ChoiceBox<String> getChoiceBoxFacturada() { return choiceBoxFacturada; }
    public Label getLabelFactura() { return labelFactura; }
    public Image getImageLabel() { return imageLabel; }
    public void setImageLabel(Image imageLabel) { this.imageLabel = imageLabel; }
    public ImageView getImageView() { return imageView; }
    public Button getBtnFactura() { return btnFactura; }
    public Button getBtnCobrarFactura() { return btnCobrarFactura; }
}
