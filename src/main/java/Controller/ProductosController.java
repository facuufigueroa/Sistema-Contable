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
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

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
    private TableView<Producto> tablaProductos;
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

    ObservableList<Producto> productoFiltradoPorNombre;

    private ServiceProducto serviceProducto = new ServiceProducto();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productoFiltradoPorCodigo = FXCollections.observableArrayList();
        productoFiltradoPorNombre = FXCollections.observableArrayList();
        iniciarCbbAlicuota();
        soloNumeros(txtCodigo);
        soloNumeros(txtStock);
        soloDouble(txtPrecio);
        listarProductos();
    }

    @FXML
    public void accionGuardarProducto() throws SQLException {
        if (!verificarCamposVacios()) {
            if(!existeProducto(txtCodigo.getText())) {
                    Producto producto = new Producto(Long.parseLong(txtCodigo.getText()), txtNombre.getText(), txtDetalle.getText(), Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()), Double.parseDouble(String.valueOf(comboBoxAlicuota.getValue())));
                    serviceProducto.insertarProducto(producto);
                    listarProductos();
                    limparTodosLosCampos();
                    Alerta.alertaProductoRegistrado();
                }
                else{
                Alerta.alertaExisteProducto();
                }
            }
        else {
            Alerta.alertaCamposVaciosProducto();
        }
    }

    public void listarProductos() {
        productObservableList = FXCollections.observableArrayList(serviceProducto.listarProductosHabilitados());
        columCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
        columNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<Producto, String>("precio"));
        columStock.setCellValueFactory(new PropertyValueFactory<Producto, String>("stock"));
        columEstado.setCellValueFactory(new PropertyValueFactory<Producto, String>("estado"));
        tablaProductos.setItems(productObservableList);
    }

    public void iniciarCbbAlicuota() {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("21.0", "10.50", "27.0");
        comboBoxAlicuota.setItems(items);
    }


    public Boolean verificarCamposVacios() {
        return txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() ||
                txtDetalle.getText().isEmpty() || txtPrecio.getText().isEmpty()
                || txtStock.getText().isEmpty() || comboBoxAlicuota.getValue() == null;
    }


    /*Búsquedas Filtradas*/
    @FXML
    public void buscarPorCodigo() {
        String filtroCodigo = txtBuscarPorCodigo.getText();
        try {
            if (filtroCodigo.isEmpty()) {
                tablaProductos.setItems(productObservableList);
            } else {
                productoFiltradoPorCodigo.clear();
                for (Producto p : productObservableList) {
                    String codigoP = String.valueOf(p.getCodigo());
                    if (codigoP.contains(filtroCodigo)) {
                        productoFiltradoPorCodigo.add(p);
                    }
                }
                tablaProductos.setItems(productoFiltradoPorCodigo);
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
                productoFiltradoPorCodigo.clear();
                for (Producto p : productObservableList) {
                    String nombreP = String.valueOf(p.getNombre());
                    if (nombreP.toLowerCase().contains(filtroNombre.toLowerCase())) {
                        productoFiltradoPorCodigo.add(p);
                    }
                }
                tablaProductos.setItems(productoFiltradoPorCodigo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void accionLimpiarBusqueda() {
        limpiarCamposBusqueda();
    }
    /* ---------------------------- */

    public void limpiarCamposBusqueda() {
        txtBuscarPorCodigo.setText("");
        txtBuscarPorNombre.setText("");
        listarProductos();
    }

    public void limparTodosLosCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDetalle.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        comboBoxAlicuota.setItems(null);
        iniciarCbbAlicuota();
    }

    @FXML
    public void accionEditarProducto() {
        traerProductoSeleccionFila();
    }

    @FXML
    public void accionModificarProducto(){
        if(!verificarCamposVacios()){
            Producto producto = new Producto(txtNombre.getText(),txtDetalle.getText(),Double.parseDouble(txtPrecio.getText()), Integer.parseInt(txtStock.getText()),Double.parseDouble(String.valueOf(comboBoxAlicuota.getValue())));
            serviceProducto.modificarProducto(txtCodigo.getText(),producto);

            limparTodosLosCampos();
            txtCodigo.setDisable(false);
            comboBoxAlicuota.setDisable(false);
            Alerta.alertaProductoModificado();
        }
        else{
            Alerta.alertaCamposVaciosProducto();
        }
    }


    /*Método para traer el producto seleccionado en la fila*/
    public void traerProductoSeleccionFila() {
        try {
            limparTodosLosCampos();
            txtCodigo.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getCodigo()));
            txtNombre.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getNombre()));
            txtDetalle.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getDetalle()));
            txtPrecio.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getPrecio()));
            txtStock.setText(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getStock()));

            txtCodigo.setDisable(true);
            comboBoxAlicuota.getSelectionModel().select(obtenerAlicuotaProducto(String.valueOf(tablaProductos.getSelectionModel().getSelectedItem().getCodigo())));


        } catch (NullPointerException e) {
        }

    }

    public String obtenerAlicuotaProducto(String codigoProducto){
            String alicuotaP = serviceProducto.obtenerAlicuotaProducto(codigoProducto);
            return  alicuotaP;
    }

    public boolean existeProducto(String codigo){
        boolean existencia = serviceProducto.existeProducto(codigo);
        if(existencia == true){
            return true;
        }
        return false;
    }

    @FXML
    public void accionDeshabilitarProducto(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmación de deshabilitación de producto");
        alert.initStyle(StageStyle.TRANSPARENT);
        List<Producto> filaSeleccionada = tablaProductos.getSelectionModel().getSelectedItems();
        if(filaSeleccionada.size() == 1 ) {
                alert.setContentText("¿Está seguro de deshabilitar el producto: " + filaSeleccionada.get(0).getNombre() + "?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        serviceProducto.deshabilitarProducto(filaSeleccionada.get(0).getCodigo().toString());
                        Alerta.alertaProductoDeshabilitado(filaSeleccionada.get(0).getNombre());
                        listarProductos();
                    }
                });
                }
    }

    @FXML
    public void accionHabilitarProducto(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmación de habilitacion de producto");
        alert.initStyle(StageStyle.TRANSPARENT);
        List<Producto> filaSeleccionada = tablaProductos.getSelectionModel().getSelectedItems();
        if(filaSeleccionada.size() == 1 ) {
            alert.setContentText("¿Está seguro de habilitar el producto: " + filaSeleccionada.get(0).getNombre() + "?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    serviceProducto.habilitarProducto(filaSeleccionada.get(0).getCodigo().toString());
                    Alerta.alertaProductoHabilitado(filaSeleccionada.get(0).getNombre());
                    listarProductos();
                }
            });
        }
    }

    /*Solo campos numericos*/
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

    public static void soloDouble(TextField txtMonto) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*\\.?\\d{0,2}$")) {
                return change;
            }
            return null;
        };
        txtMonto.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), null, integerFilter));
    }



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
