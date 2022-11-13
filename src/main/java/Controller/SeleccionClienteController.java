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

public class SeleccionClienteController extends ViewFuntionality implements Initializable {

    @FXML
    private Button btnContinuar;
    @FXML
    private TextField txtBuscarPorCodigo;
    @FXML
    private TextField txtBuscarPorNombre;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView tablaClientes;
    @FXML
    private Button btnVolver;

    private SeleccionarProductoController seleccionarProductoController;

    private HomeVentasController homeVentasController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void hideStage(){ getVentana().hide(); }


    /*Método que continua a la vista para seleccionar el o los productos a vender*/
    @FXML
    public void accionContinuar(ActionEvent event) throws IOException {
        accionContinuarSeleccionProduct(event);
    }

    public void accionContinuarSeleccionProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-productos.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionarProductoController(loadSeleccionProducto(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage seleccionProducto = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionarProductoController().setVentana(seleccionProducto);
        getSeleccionarProductoController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    private SeleccionarProductoController loadSeleccionProducto(SeleccionarProductoController ventasController){ return ventasController; }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(loadHomeVentas(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeVentas = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeVentasController().setVentana(homeVentas);
        getHomeVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private HomeVentasController loadHomeVentas(HomeVentasController homeVentas){ return homeVentas; }


    @FXML
    public void accionLimpiarCampos(){}

    public SeleccionarProductoController getSeleccionarProductoController() {
        return seleccionarProductoController;
    }

    public void setSeleccionarProductoController(SeleccionarProductoController seleccionarProductoController) {
        this.seleccionarProductoController = seleccionarProductoController;
    }

    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }
}
