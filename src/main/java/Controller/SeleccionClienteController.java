package Controller;

import Model.Alerta;
import Model.Ventas.Cliente;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class SeleccionClienteController extends ViewFuntionality implements Initializable {

    @FXML
    private Button btnContinuar;
    @FXML
    private TextField txtBuscarPorDNI;
    @FXML
    private TextField txtBuscarPorNombre;
    @FXML
    private TextField txtClienteSeleccionado;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private Button btnVolver;

    private SeleccionarProductoController seleccionarProductoController;

    private HomeVentasController homeVentasController;

    ObservableList<Cliente> clienteObservableList;

    ObservableList<Cliente> clienteFiltrado;

    @FXML
    private TableColumn columDNI;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columRazonSocial;

    private ServiceCliente serviceCliente = new ServiceCliente();

    private Venta venta = Venta.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteFiltrado = FXCollections.observableArrayList();
        listarClientesHabilitados();
        soloNumeros(txtBuscarPorDNI);
    }
    public void hideStage(){ getVentana().hide(); }


    /*Método que continua a la vista para seleccionar el o los productos a vender*/
    @FXML
    public void accionContinuar(ActionEvent event) throws IOException {
        if (!verificarSeleccionCliente()) {
            obtenerClienteSeleccionado();
            accionContinuarSeleccionProduct(event);
        }
        else{
            Alerta.alertaSeleccioneCliente();
        }
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

    public void listarClientesHabilitados() {
        clienteObservableList = FXCollections.observableArrayList(serviceCliente.listarClientesHabilitados());
        columDNI.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dni"));
        columNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        columRazonSocial.setCellValueFactory(new PropertyValueFactory<Cliente, String>("razonSocial"));
        tablaClientes.setItems(clienteObservableList);
    }

    public void obtenerClienteSeleccionado(){
        String nombreCliente = "";
        try {
            nombreCliente = txtClienteSeleccionado.getText();
            int idCliente = serviceCliente.obtenerIdCliente(nombreCliente);
            venta.setIdCliente(idCliente);
        } catch (NullPointerException e) {
        }
    }

    @FXML
    public void accionSeleccionarCliente(){
        if(!tablaClientes.getSelectionModel().isEmpty()) {
            String nombre = tablaClientes.getSelectionModel().getSelectedItem().getNombre();
            txtClienteSeleccionado.setText(nombre);
        } else{
            Alerta.alertaSeleccioneCliente();
        }
    }
    @FXML
    public void accionLimpiarCampos(){
        txtBuscarPorDNI.setText("");
        txtBuscarPorNombre.setText("");
        listarClientesHabilitados();
        txtClienteSeleccionado.setText("");
    }

    public Boolean verificarSeleccionCliente() {
        return txtClienteSeleccionado.getText().isEmpty();
    }

    @FXML
    public void buscarPorNombre() {
        String filtroNombre = txtBuscarPorNombre.getText();
        try {
            if (filtroNombre.isEmpty()) {
                tablaClientes.setItems(clienteObservableList);
            } else {
                clienteFiltrado.clear();
                for (Cliente c : clienteObservableList) {
                    String nombreC = String.valueOf(c.getNombre());
                    if (nombreC.toLowerCase().contains(filtroNombre.toLowerCase())) {
                        clienteFiltrado.add(c);
                    }
                }
                tablaClientes.setItems(clienteFiltrado);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void buscarPorDNI() {
        String filtroDNI = txtBuscarPorDNI.getText();
        try {
            if (filtroDNI.isEmpty()) {
                tablaClientes.setItems(clienteObservableList);
            } else {
                clienteFiltrado.clear();
                for (Cliente c : clienteObservableList) {
                    String DNI = String.valueOf(c.getDni());
                    if (DNI.contains(filtroDNI)) {
                        clienteFiltrado.add(c);
                    }
                }
                tablaClientes.setItems(clienteFiltrado);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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

    public void accionAgregarCliente(ActionEvent actionEvent) {
    }
}
