package Controller;

import Model.Alerta;
import Model.Producto;
import Model.User;
import Model.Ventas.TablaVistaVenta;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentasController extends ViewFuntionality implements Initializable {

    private HomeVentasController homeVentasController;

    private SeleccionarPagoController seleccionarPagoController;

    private SeleccionClienteController seleccionClienteController;

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
    private TableView<TablaVistaVenta> tablaVenta;
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
    private User user = User.getInstance();

    private ServiceCliente serviceCliente = new ServiceCliente();

    private ServiceVenta serviceVenta = new ServiceVenta();

    private ServiceProducto serviceProducto = new ServiceProducto();

    private ObservableList<TablaVistaVenta> tablaVentaObservableList;
    ArrayList<TablaVistaVenta> ventaProductos = venta.getVentaProductos();

    ArrayList<Producto> productos = venta.getProductos();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosVenta();
    }
    public void cargarDatosVenta(){
        txtCondicionIva.setText(serviceCliente.obtenerCondicionIva(venta.getIdCliente()));
        txtCliente.setText(serviceCliente.obtenerNombreCliente(venta.getIdCliente()));
        txtFormaPago.setText(obtenerTxtFP());
        listarProductosVenta();
        txtIVA.setText(obtenerIVA().toString());
        venta.setTotalBruto(obtenerTotalVenta());
        venta.setTotalNeto((obtenerTotalVenta() + obtenerIVA()));
        txtTotal.setText(String.valueOf(venta.getTotalNeto()));
        venta.setTotales(obtenerTotales(venta.getCuotas()));
    }

    public String obtenerTxtFP(){
        String formaPago = obtenerFormaPago(venta.getFormaPago());
       if(formaPago.toUpperCase() == "CUOTAS"){
          return formaPago + " " + venta.getCuotas();
       }
       else{
           return formaPago;
       }
    }
    public String obtenerFormaPago(int formaPago){
        return serviceVenta.obtenerFormaPago(formaPago);
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
            TablaVistaVenta producto = tablaVenta.getSelectionModel().getSelectedItem();
            ventaProductos.remove(tablaVenta.getSelectionModel().getSelectedIndex());
            productos.remove(serviceProducto.obtenerProductoPorId(producto.getIdProducto()));
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

    private SeleccionClienteController loadSeleccionCliente(SeleccionClienteController selectClienteController){ return selectClienteController; }

    public SeleccionClienteController getSeleccionClienteController() {
        return seleccionClienteController;
    }

    public void setSeleccionClienteController(SeleccionClienteController seleccionClienteController) {
        this.seleccionClienteController = seleccionClienteController;
    }

    public void accionBtnGenerarVenta(ActionEvent actionEvent) throws SQLException {
            Venta venta1 = new Venta(venta.getIdCliente(), venta.getTotalBruto(), venta.getTotalNeto(),
                    venta.getTotales(), venta.getFormaPago(), user.getId());
            serviceVenta.insertarVenta(venta1);
            insertarVentaProducto();
            Alerta.alertaVentaRegistrada();
    }

    public void insertarVentaProducto() throws SQLException{
        int idVenta = serviceVenta.obtenerIdVenta();
            for (TablaVistaVenta venta1 : ventaProductos) {
                TablaVistaVenta ventaProducto = new TablaVistaVenta(idVenta, venta1.getIdProducto(),
                        venta1.getCantidad(), venta1.getPrecioUnitario(),venta1.getPrecioTotal());
                serviceVenta.insertarVenta_producto(ventaProducto);
            }
    }
    public Double obtenerTotalVenta(){
        Double total = 0.0;
        for(TablaVistaVenta producto : ventaProductos){
            total += producto.getPrecioTotal();
        }
        return total;
    }
    public Double obtenerIVA(){
        Double totalIva = 0.0;
        for (Producto p: productos){
            for (TablaVistaVenta tv:ventaProductos) {
                if(serviceProducto.obtenerIdProducto(p.getCodigo()) == tv.getIdProducto()) {
                    totalIva += ((p.getAlicuota() * p.getPrecio()) / 100) * tv.getCantidad();
                }
            }
        }
        return totalIva;
    }

    public Double obtenerTotales(int cuotas) {
        if (cuotas != 0) {
            return (Double.parseDouble(txtTotal.getText()) / cuotas);
        }
        else {
            return Double.parseDouble(txtTotal.getText());
        }
    }
    public void accionBtnNuevaVenta(ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-clientes.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionClienteController(loadSeleccionCliente(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage pagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionClienteController().setVentana(pagoStage);
        getSeleccionClienteController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}
