package Controller;
import Model.Ventas.Venta;
import Model.ViewFuntionality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeleccionarPagoController extends ViewFuntionality implements Initializable {

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnContinuar;
    @FXML
    private ComboBox comboBoxSeleccionarPago;
    @FXML
    private TextField txtCantidadCuotas;

    private SeleccionarProductoController seleccionarProductoController;

    private VentasController ventasController;

    private Venta venta = Venta.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarCbbFormaPago();
        txtCantidadCuotas.setDisable(true);
        txtCantidadCuotas.setText("0");
    }
    public void hideStage(){ getVentana().hide(); }

    /*Método para habilitar el campo cantidad de cuotas si la forma de pago elegida es en cuotas*/
    public void accionCbbFormaPago(){
        if(!comboBoxSeleccionarPago.getSelectionModel().isEmpty()){
            String seleccion = (String) comboBoxSeleccionarPago.getValue();
            evaluarSiEsCuotas(seleccion);
        }
    }

    public void evaluarSiEsCuotas(String nombre){
        if(nombre.equals("Cuotas")){
            txtCantidadCuotas.setDisable(false);
            txtCantidadCuotas.setText("");
        }
        else{
            txtCantidadCuotas.setText("0");
            txtCantidadCuotas.setDisable(true);
        }
    }


    public void iniciarCbbFormaPago(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Efectivo", "Cuotas","Tarjeta Crédito","Tarjeta Débito","Cheque","Pagaré");
        comboBoxSeleccionarPago.setItems(items);
    }


    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-productos.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionarProductoController(loadSeleccionarProduct(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage pagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionarProductoController().setVentana(pagoStage);
        getSeleccionarProductoController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private SeleccionarProductoController loadSeleccionarProduct(SeleccionarProductoController productController){ return productController; }

    /*Método que continua a la vista para generar una nueva venta*/
    @FXML
    public void accionContinuar(ActionEvent event) throws IOException {
        obtenerFormaPago();
        continuarNewVentas(event);
    }


    public void continuarNewVentas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/nueva-venta.fxml"));
        Parent parent = fxmlLoader.load();
        setVentasController(loadNewVenta(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage newVentaStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getVentasController().setVentana(newVentaStage);
        getVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void obtenerFormaPago(){
        try {
            String formaPago = comboBoxSeleccionarPago.getSelectionModel().getSelectedItem().toString();
            venta.setFormaPago(formaPago);
        } catch (NullPointerException e) {
        }
    }

    private VentasController loadNewVenta(VentasController ventaController){ return ventaController; }

    public SeleccionarProductoController getSeleccionarProductoController() {
        return seleccionarProductoController;
    }

    public void setSeleccionarProductoController(SeleccionarProductoController seleccionarProductoController) {
        this.seleccionarProductoController = seleccionarProductoController;
    }

    public VentasController getVentasController() {
        return ventasController;
    }

    public void setVentasController(VentasController ventasController) {
        this.ventasController = ventasController;
    }
}
