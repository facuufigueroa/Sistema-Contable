package Controller;

import Model.Alerta;
import Model.Cuenta;
import Model.Producto;
import Model.ViewFuntionality;
import Services.ServiceProducto;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductosController extends ViewFuntionality implements Initializable {

    private HomeVentasController homeVentasController;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDetalle;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtBuscarPorCodigo;
    @FXML
    private TextField txtBuscarPorNombre;
    @FXML
    private ComboBox comboBoxAlicuota;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnHabilitar;
    @FXML
    private Button btnDeshabilitar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn columCodigo;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columPrecio;
    @FXML
    private TableColumn columStock;
    @FXML
    private TableColumn columEstado;

    ObservableList<Producto> productObservableList;
    ObservableList<Producto> productoFiltradoPorCodigo;

    private ServiceProducto serviceProducto = new ServiceProducto();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarCbbAlicuota();
        listarProductoHabilitados();
    }

    @FXML
    public void accionGuardarProducto() throws SQLException {
        if(!verificarCamposVacios()){
            Producto producto = new Producto(Long.parseLong(txtCodigo.getText()),txtNombre.getText(),txtDetalle.getText(),Double.parseDouble(txtPrecio.getText()),Integer.parseInt(txtStock.getText()),Double.parseDouble(String.valueOf(comboBoxAlicuota.getValue())));
            serviceProducto.insertarProducto(producto);
            listarProductoHabilitados();
            Alerta.alertaProductoRegistrado();
        }
        else{
            Alerta.alertaCamposVaciosProducto();
        }
    }

    public void listarProductoHabilitados(){
        productObservableList = FXCollections.observableArrayList(serviceProducto.listarProductosHabilitados());
        columCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        columNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
        columStock.setCellValueFactory(new PropertyValueFactory<Producto, String>("stock"));
        columEstado.setCellValueFactory(new PropertyValueFactory<Producto, String>("estado"));
        tablaProductos.setItems(productObservableList);
    }

    public void iniciarCbbAlicuota(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("21.0", "10.50","27.0");
        comboBoxAlicuota.setItems(items);
    }


    public Boolean verificarCamposVacios(){
        return txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtDetalle.getText().isEmpty() || txtPrecio.getText().isEmpty()
                || txtStock.getText().isEmpty() || comboBoxAlicuota.getValue() == null;
    }


    /*Búsquedas Filtradas*/
    @FXML
    public void  buscarPorCodigo(){
        String filtroCodigo = txtCodigo.getText();
        try{
            if(filtroCodigo.isEmpty()){
                tablaProductos.setItems(productObservableList);
            }
            else{
                productoFiltradoPorCodigo.clear();
                for(Producto p : productObservableList){
                    String codigoP=String.valueOf(p.getCodigo());
                    if(codigoP.contains(filtroCodigo)){
                        productoFiltradoPorCodigo.add(p);
                    }
                }
                tablaProductos.setItems(productoFiltradoPorCodigo);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void buscarPorNombre(){

    }

    @FXML
    public void accionLimpiarBusqueda(){

    }
    /* ---------------------------- */


    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(loadMenuVenta(fxmlLoader.getController()));
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
    private HomeVentasController loadMenuVenta(HomeVentasController menuVentaseController){ return menuVentaseController; }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }


    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }
}
