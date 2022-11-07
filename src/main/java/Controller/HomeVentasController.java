package Controller;

import Model.User;
import Model.ViewFuntionality;
import Services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeVentasController extends ViewFuntionality implements Initializable {

    private HomeController homeController;

    @FXML
    private Button btnRegistrarVenta;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnFacturas;

    @FXML
    private Label txtUsuarioLogueado;

    @FXML
    private Button btnVolverMenu;

    private VentasController ventasController;

    private ClientesController clientesController;

    private ProductosController productosController;

    private Service service = new Service();
    private User u = User.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cambiarTexto();
    }

    public void hideStage(){ getVentana().hide(); }

    @FXML
    public void accionBtnVolver(ActionEvent event) throws IOException {
        loadHomePrincipal(event);
    }

    private void loadHomePrincipal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas-asientos.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeController(loadHome(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeController().setVentana(homeStage);
        getHomeController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    private HomeController loadHome(HomeController homeController){ return homeController; }


    /* Acción de btn para registrar nuevas ventas */
    @FXML
    public void loadNewVenta(ActionEvent event) throws IOException {
        loadVentasRegister(event);
    }

    private void loadVentasRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/nueva-venta.fxml"));
        Parent parent = fxmlLoader.load();
        setVentasController(loadVentaNew(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getVentasController().setVentana(homeStage);
        getVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    private VentasController loadVentaNew(VentasController ventasController){ return ventasController; }

    /* Fin */


    /* Acción de btn para registrar nuevos clientes */

    @FXML
    public void loadNewCliente(ActionEvent event) throws IOException {
        loadClientesRegister(event);
    }

    public void loadClientesRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/registro-clientes.fxml"));
        Parent parent = fxmlLoader.load();
        setClientesController(loadClientesNew(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getClientesController().setVentana(homeStage);
        getClientesController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    public ClientesController loadClientesNew(ClientesController clientesController){ return clientesController; }

    /* -------- Fin ----------------*/

    /* Acción de btn para registrar nuevos productos */
    @FXML
    public void loadNewProducto(ActionEvent event) throws IOException {
        loadProductoRegister(event);
    }

    public void loadProductoRegister(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/registro-productos.fxml"));
        Parent parent = fxmlLoader.load();
        setProductosController(loadProductosNew(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getProductosController().setVentana(homeStage);
        getProductosController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    public ProductosController loadProductosNew(ProductosController productosController){ return productosController; }

    /* ------- Fin -----------*/

    private void cambiarTexto(){ txtUsuarioLogueado.setText(u.getNombre().toUpperCase()+" "+u.getApellido().toUpperCase()+" "+"| "+service.obtenerRolPorEmail(u.getEmail()).toUpperCase()); }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public VentasController getVentasController() {
        return ventasController;
    }

    public void setVentasController(VentasController ventasController) {
        this.ventasController = ventasController;
    }

    public ClientesController getClientesController() {
        return clientesController;
    }

    public void setClientesController(ClientesController clientesController) {
        this.clientesController = clientesController;
    }

    public ProductosController getProductosController() {
        return productosController;
    }

    public void setProductosController(ProductosController productosController) {
        this.productosController = productosController;
    }
}
