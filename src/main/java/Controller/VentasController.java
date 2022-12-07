package Controller;

import Model.Ventas.TablaVistaVenta;
import Model.Ventas.Venta;
import Model.ViewFuntionality;
import Services.Ventas.ServiceCliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentasController extends ViewFuntionality implements Initializable {

    private HomeVentasController homeVentasController;

    private SeleccionarPagoController seleccionarPagoController;

    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtFormaPago;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtCondicionIva;
    @FXML
    private TextField txtIVA;
    @FXML
    private TableView tablaVenta;
    @FXML
    private TableColumn columProducto;
    @FXML
    private TableColumn columDescripcion;
    @FXML
    private TableColumn columCantidad;
    @FXML
    private TableColumn columPrecio;
    @FXML
    private TableColumn columTotal;
    private Venta venta = Venta.getInstance();

    private ServiceCliente serviceCliente = new ServiceCliente();

    private ObservableList<TablaVistaVenta> tablaVentaObservableList;
    ArrayList<TablaVistaVenta> ventaProductos = venta.getVentaProductos();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosVenta();
    }
    public void cargarDatosVenta(){
        txtCondicionIva.setText(serviceCliente.obtenerCondicionIva(venta.getIdCliente()));
        txtCliente.setText(serviceCliente.obtenerNombreCliente(venta.getIdCliente()));
        txtFormaPago.setText(venta.getFormaPago());
        listarProductosVenta();
        txtTotal.setText(String.valueOf((venta.obtenerTotalVenta() + venta.obtenerIVA())));
        txtIVA.setText(venta.obtenerIVA().toString());
    }

    public void listarProductosVenta(){
        tablaVentaObservableList = FXCollections.observableArrayList(ventaProductos);
        columProducto.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta, String>("producto"));
        columDescripcion.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta, String>("descripcion"));
        columCantidad.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta,Integer>("cantidad"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta,Double>("precioUnitario"));
        columTotal.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta, Double>("precioTotal"));
        tablaVenta.setItems(tablaVentaObservableList);
    }
    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-forma-pago.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionarPagoController(loadSeleccionFormaPago(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage formaPagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionarPagoController().setVentana(formaPagoStage);
        getSeleccionarPagoController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    public void accionBtnQuitarProducto(){
        try {
            ventaProductos.remove(tablaVenta.getSelectionModel().getSelectedIndex());
            cargarDatosVenta();
        }
        catch (NullPointerException e){
        }
    }

    private SeleccionarPagoController loadSeleccionFormaPago(SeleccionarPagoController selectPagoController){ return selectPagoController; }


    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }

    public SeleccionarPagoController getSeleccionarPagoController() {
        return seleccionarPagoController;
    }

    public void setSeleccionarPagoController(SeleccionarPagoController seleccionarPagoController) {
        this.seleccionarPagoController = seleccionarPagoController;
    }
}
