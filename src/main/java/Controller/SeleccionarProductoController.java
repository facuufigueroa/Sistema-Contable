package Controller;

import Model.Alerta;
import Model.Producto;
import Model.ProductoAgregado;
import Model.Ventas.TablaVistaVenta;
import Model.Ventas.Venta;
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
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.UnaryOperator;

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
    private TableView<ProductoAgregado> tablaProductos;
    @FXML
    private TableView<ProductoAgregado> tablaProductosAgregados;
    @FXML
    private TableColumn columCodigo;
    @FXML
    private TableColumn columProducto;
    @FXML
    private TableColumn columStock;
    @FXML
    private TableColumn columCodigoAgregado;
    @FXML
    private TableColumn columProductoAgregado;
    @FXML
    private TableColumn columCantidadAgregado;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnQuitarProducto;
    @FXML
    private TextField txtCantidadProductos;

    ObservableList<ProductoAgregado> productoFiltrado;

    ArrayList<ProductoAgregado> productosAgregados = new ArrayList<>();

    ArrayList<ProductoAgregado> listadoProductos = new ArrayList<>();

    ObservableList<ProductoAgregado> productObservableList;


    private SeleccionarPagoController seleccionarPagoController;

    private SeleccionClienteController seleccionarClienteController;

    private ServiceProducto serviceProducto = new ServiceProducto();

    private Venta venta = Venta.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productoFiltrado = FXCollections.observableArrayList();
        insertarProductos();
        listarProductosHabilitados();
        soloNumeros(txtBuscarPorCodigo);
        soloNumeros(txtCantidadProductos);
    }
    public void hideStage(){ getVentana().hide(); }
    @FXML
    public void accionBtnAgregarProducto() {
        if (!verificarCantidadVacia()) {
            insertarProductosAgregados(obtenerProductoSeleccionado());
            vaciarCampos();
        }
        else {
            Alerta.alertaCantidadVacia();
        }
    }
    public Boolean verificarCantidadVacia() {
        return txtCantidadProductos.getText().isEmpty();
    }

    public Boolean verificarTablaVacia(){
        return tablaProductosAgregados.getItems().isEmpty();
    }

    public void vaciarCampos(){
        txtCantidadProductos.setText("");
        txtBuscarPorNombre.setText("");
        txtBuscarPorCodigo.setText("");
        listarProductosHabilitados();
    }
    public ProductoAgregado obtenerProductoSeleccionado(){
            ProductoAgregado productoAgregado = new ProductoAgregado();
        try {
            productoAgregado.setCodigo(tablaProductos.getSelectionModel().getSelectedItem().getCodigo());
            productoAgregado.setNombre(tablaProductos.getSelectionModel().getSelectedItem().getNombre());
            productoAgregado.setCantidad(Integer.valueOf(txtCantidadProductos.getText()));
            return productoAgregado;
        }
        catch (NullPointerException e){

        }
        return productoAgregado;
    }

    @FXML
    public void accionBtnQuitarProducto(){
        try {
            productosAgregados.remove(tablaProductosAgregados.getSelectionModel().getSelectedIndex());
            listarProductosAgregados();
        }
        catch (NullPointerException e){
        }
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
        if (!verificarTablaVacia()) {
            obtenerProductos();
            continuarASeleccionPago(event);
        }
        else{
            Alerta.alertaSeleccioneProductos();
        }
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

    public void listarProductosHabilitados() {
        productObservableList = FXCollections.observableArrayList(listadoProductos);
        columCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        columProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        columStock.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("stock"));
        tablaProductos.setItems(productObservableList);
    }
    public void listarProductosAgregados(){
        ObservableList productoAgregadoObservableList = FXCollections.observableArrayList(productosAgregados);
        columCodigoAgregado.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        columProductoAgregado.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        columCantidadAgregado.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidad"));
        tablaProductosAgregados.setItems(productoAgregadoObservableList);
    }

    public void insertarProductos() {
        for(Producto p:serviceProducto.listarProductosHabilitados()) {
            int idProducto = serviceProducto.obtenerIdProducto(p.getCodigo());
            int stock= serviceProducto.obtenerStockProducto(idProducto);
            ProductoAgregado producto = new ProductoAgregado(p.getCodigo(),p.getNombre(),0,stock);
            listadoProductos.add(producto);
        }
    }


    public void insertarProductosAgregados(ProductoAgregado productoAgregado) {
        int stockActual = tablaProductos.getSelectionModel().getSelectedItem().getStock();
        int cantidadRequerida = Integer.valueOf(txtCantidadProductos.getText());
        if (stockActual >= cantidadRequerida) {
            productosAgregados.add(productoAgregado);
            listarProductosAgregados();
        }
        else {
            Alerta.alertaStockInsuficiente();
        }
    }
    @FXML
    public void buscarPorCodigo() {
        String filtroCodigo = txtBuscarPorCodigo.getText();
        try {
            if (filtroCodigo.isEmpty()) {
                tablaProductos.setItems(productObservableList);
            } else {
                productoFiltrado.clear();
                for (ProductoAgregado p : productObservableList) {
                    String codigoP = String.valueOf(p.getCodigo());
                    if (codigoP.contains(filtroCodigo)) {
                        productoFiltrado.add(p);
                    }
                }
                tablaProductos.setItems(productoFiltrado);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void buscarPorNombre() {
        String filtroNombre = txtBuscarPorNombre.getText();
        try {
            if (filtroNombre.isEmpty()) {
                tablaProductos.setItems(productObservableList);
            } else {
                productoFiltrado.clear();
                for (ProductoAgregado p : productObservableList) {
                    String nombreP = String.valueOf(p.getNombre());
                    if (nombreP.toLowerCase().contains(filtroNombre.toLowerCase())) {
                        productoFiltrado.add(p);
                    }
                }
                tablaProductos.setItems(productoFiltrado);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    public void accionBtnLimpiar() {
        txtBuscarPorCodigo.setText("");
        txtBuscarPorNombre.setText("");
        listarProductosHabilitados();
    }

    public void obtenerProductos(){
        ArrayList<Producto> productosSeleccionados = new ArrayList<Producto>();
        ArrayList<TablaVistaVenta> ventaProductos = new ArrayList<>();
        TablaVistaVenta ventaP;
        for (ProductoAgregado p: productosAgregados) {
            productosSeleccionados.add(serviceProducto.obtenerProductoPorCodigo(p.getCodigo()));
            Double precio = serviceProducto.obtenerProductoPorCodigo(p.getCodigo()).getPrecio();
            String nombre = serviceProducto.obtenerProductoPorCodigo(p.getCodigo()).getNombre();
            String descripcion = serviceProducto.obtenerProductoPorCodigo(p.getCodigo()).getDetalle();
            ventaP = new TablaVistaVenta(nombre,descripcion,p.getCantidad(),precio,precio*p.getCantidad(),serviceProducto.obtenerIdProducto(p.getCodigo()));
            ventaProductos.add(ventaP);
        }
        venta.setProductos(productosSeleccionados);
        venta.setVentaProductos(ventaProductos);
    }

    public static void soloNumeros(TextField txtCodigo) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*$")) {
                return change;
            }
            return null;
        };
        txtCodigo.setTextFormatter(
                new TextFormatter<Integer>(
                        new IntegerStringConverter(), null, integerFilter));
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
