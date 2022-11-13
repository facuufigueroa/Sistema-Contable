package Controller;

import Model.ViewFuntionality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SeleccionarProductoController extends ViewFuntionality implements Initializable {

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnContinuar;
    @FXML
    private TextField txtBuscarPorCodigo;
    @FXML
    private TextField txtBuscarPorNombre;
    @FXML
    private TableView tablaProductos;
    @FXML
    private TableView tablaProductosAgregados;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnQuitarProducto;
    @FXML
    private TextField txtCantidadProductos;

    private SeleccionarPagoController seleccionarPagoController;

    private SeleccionClienteController seleccionarClienteController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void hideStage(){ getVentana().hide(); }
    @FXML
    public void accionBtnAgregarProducto(){

    }
    @FXML
    public void accionBtnQuitarProducto(){

    }
    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-clientes.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionarClienteController(loadSeleccionarCliente(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage pagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionarClienteController().setVentana(pagoStage);
        getSeleccionarClienteController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private SeleccionClienteController loadSeleccionarCliente(SeleccionClienteController clientController){ return clientController; }


    /*Método que continua a la vista para seleccionar la forma de pago*/
    @FXML
    public void accionContinuar(ActionEvent event) throws IOException {
        continuarASeleccionPago(event);
    }

    public void continuarASeleccionPago(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-forma-pago.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionarPagoController(loadSeleccionarPago(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage pagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionarPagoController().setVentana(pagoStage);
        getSeleccionarPagoController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private SeleccionarPagoController loadSeleccionarPago(SeleccionarPagoController pagoController){ return pagoController; }

    @FXML
    public void accionBtnLimpiar(){

    }

    public SeleccionarPagoController getSeleccionarPagoController() {
        return seleccionarPagoController;
    }

    public void setSeleccionarPagoController(SeleccionarPagoController seleccionarPagoController) {
        this.seleccionarPagoController = seleccionarPagoController;
    }

    public SeleccionClienteController getSeleccionarClienteController() {
        return seleccionarClienteController;
    }

    public void setSeleccionarClienteController(SeleccionClienteController seleccionarClienteController) {
        this.seleccionarClienteController = seleccionarClienteController;
    }
}
