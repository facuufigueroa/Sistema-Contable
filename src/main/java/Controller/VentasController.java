package Controller;

import Model.Producto;
import Model.Ventas.Venta;
import Model.ViewFuntionality;
import Services.ServiceProducto;
import Services.Ventas.ServiceCliente;
import Services.Ventas.ServiceVenta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentasController extends ViewFuntionality implements Initializable {

    private HomeVentasController homeVentasController;

    private SeleccionarPagoController seleccionarPagoController;

    private SeleccionClienteController seleccionClienteController;

    private SeleccionarProductoController seleccionarProductoController;

    @FXML
    private TextField txtNombre1;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFormaPago;
   // @FXML
    //private TextField txtPrecio1;
    @FXML
    private TextField txtCondicionIva;
    //@FXML
    //private TextField txtStockDisponible;


    private Venta venta = Venta.getInstance();

    private ServiceCliente serviceCliente;

    private ServiceProducto serviceProducto;

    private ServiceVenta serviceVenta;

    private ObservableList ventaObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void cargarDatosVenta(){
        txtCondicionIva.setText(venta.getCondicionIva());
        txtNombre1.setText(venta.getNombreCliente());
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
