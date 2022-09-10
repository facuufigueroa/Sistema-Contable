package Controller;

import Model.Cuenta;
import Model.ViewFuntionality;
import Services.ServicePDC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class CuentaController extends ViewFuntionality implements Initializable {

    @FXML private Button btnMinimize;
    @FXML private Button btnClose;
    @FXML private Button btnVolver;
    @FXML private Button btnAgregarCuenta;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCodigo;
    @FXML private ComboBox cbbRecibeSaldo;
    @FXML private ComboBox cbbTipo;
    @FXML private TableView<Cuenta> tableCuentas;

    @FXML private TableColumn<Cuenta,String> columName;

    @FXML private TableColumn<Cuenta,String> columCodigo;

    @FXML private TableColumn<Cuenta,String> columRecibeSaldo;

    @FXML private TableColumn<Cuenta,String> columTipo;

    private CuentaController cuentaController;

    private ServicePDC servicePDC = new ServicePDC();

    private ObservableList <Cuenta> obCuentas = FXCollections.observableArrayList(servicePDC.listCuentasHabilitadas());

    private ObservableList<Cuenta> getObCuentas = FXCollections.observableArrayList(servicePDC.actualizarTablaCuentas());

    public void listarCuentasHabilitadas(){
        columName.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("nombre"));
        columCodigo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("codigo"));
        columRecibeSaldo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("recibe_saldo"));
        columTipo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("tipo"));
        tableCuentas.setItems(obCuentas);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarCuentasHabilitadas();
        iniciarCbbRS();
        iniciarCbbTipo();
    }

    @FXML
    public void accionVolver(){

    }

    @FXML
    public void accionAgregarCuenta() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Campos vacios");
        alert.setTitle("No seleccion de cuenta");
        alert.setContentText("Debe completar y seleccionar todos los campos para agregar cuenta" );
        alert.initStyle(StageStyle.TRANSPARENT);

        Alert alertCodigo = new Alert(Alert.AlertType.ERROR);
        alertCodigo.setHeaderText("Codigo ya existente");
        alertCodigo.setTitle("No seleccion de cuenta");
        alertCodigo.setContentText("El codigo ingresado de la cuenta ya se encuentra registrado en el sistema, por favor inserte una cuenta con diferente codigo" );
        alertCodigo.initStyle(StageStyle.TRANSPARENT);

        Cuenta cuenta = new Cuenta();
        cuenta.setNombre(txtNombre.getText());
        cuenta.setCodigo(txtCodigo.getText());
        cuenta.setRecibe_saldo(itemRecibeSaldo());
        cuenta.setTipo(itemTipo());
        try {
            if(evaluarCamposVacios()){
                alert.showAndWait();
            }
            else{
                if(servicePDC.existeCuenta(obtenerCodigoCuenta())){
                    alertCodigo.showAndWait();
                }
                else{
                    servicePDC.insertarCuenta(cuenta);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void accionDeshabilitarCuenta(){
        String codigoDeshabilitado = txtCodigo.getText();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Esta ?");
        alert.setHeaderText("Confirmación de deshabilitación de cuenta");
        alert.setTitle("No seleccion de cuenta");
        alert.setContentText("¿Está seguro de deshabilitar la cuenta con codigo: " );
        alert.initStyle(StageStyle.TRANSPARENT);

        List<Cuenta> filaSeleccionada = tableCuentas.getSelectionModel().getSelectedItems();
        if(filaSeleccionada.size() == 1 ){
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    servicePDC.deshabilitarCuenta(accionTablaCuentasH());
                    servicePDC.actualizarTablaCuentas();
                    listarCuentasHabilitadas();
                }
            });
        }
        else{
            alertaFilaNoSeleccionada();
        }
    }

    public void alertaFilaNoSeleccionada() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Cuenta NO seleccionada");
        alert.setTitle("No seleccion de cuenta");
        alert.setContentText("¡No selecciono ninguna fila de la tabla cuentas, para deshabilitar seleccione una fila!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    @FXML
    public void accionVerCuentasDeshabilitadas(){

    }

    /*Metodo para traer el codigo de cuenta seleccionado en la fila*/
    @FXML
    public String accionTablaCuentasH(){
        String codigo_cuenta = String.valueOf(tableCuentas.getSelectionModel().getSelectedItem().codigo);
        return codigo_cuenta;
    }

    /*Método para iniciar el combobox recibe saldo*/
    public void iniciarCbbRS(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Si", "No");
        cbbRecibeSaldo.setItems(items);
    }

    /*Método para iniciar el combobox tipo*/
    public void iniciarCbbTipo(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Ac", "Pa","Pm","R+","R-");
        cbbTipo.setItems(items);
    }

    /*Método que captura la selección del comboBox recibe_saldo*/
    @FXML
    public String itemRecibeSaldo(){
        String itemRecibeSaldo = (String) cbbRecibeSaldo.getSelectionModel().getSelectedItem();
        return itemRecibeSaldo;
    }

    /*Método que captura la selección del comboBox tipo*/
    @FXML
    public String itemTipo(){
        String itemTipo = (String) cbbTipo.getSelectionModel().getSelectedItem();
        return itemTipo;
    }

    /*Método que evalua campos vacios*/
    public boolean evaluarCamposVacios(){
        return Objects.equals(txtCodigo.getText(), "")
                || Objects.equals(txtNombre.getText(), "")
                || cbbTipo.getSelectionModel().getSelectedItem() == ""
                || cbbRecibeSaldo.getSelectionModel().getSelectedItem() == "";
    }

    /*Método para traer codigo de cuenta*/
    public String obtenerCodigoCuenta(){
        String codigo= txtCodigo.getText();
        return codigo;
    }

    public Button getBtnMinimize() {
        return btnMinimize;
    }

    public void setBtnMinimize(Button btnMinimize) {
        this.btnMinimize = btnMinimize;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public CuentaController getCuentaController() {
        return cuentaController;
    }

    public void setCuentaController(CuentaController cuentaController) {
        this.cuentaController = cuentaController;
    }

    public TableView getTableCuentas() {
        return tableCuentas;
    }

    public void setTableCuentas(TableView tableCuentas) {
        this.tableCuentas = tableCuentas;
    }

    public TableColumn<Cuenta, String> getColumName() {
        return columName;
    }

    public void setColumName(TableColumn<Cuenta, String> columName) {
        this.columName = columName;
    }

    public TableColumn<Cuenta, String> getColumCodigo() {
        return columCodigo;
    }

    public void setColumCodigo(TableColumn<Cuenta, String> columCodigo) {
        this.columCodigo = columCodigo;
    }

    public TableColumn<Cuenta, String> getColumRecibeSaldo() {
        return columRecibeSaldo;
    }

    public void setColumRecibeSaldo(TableColumn<Cuenta, String> columRecibeSaldo) {
        this.columRecibeSaldo = columRecibeSaldo;
    }

    public TableColumn<Cuenta, String> getColumTipo() {
        return columTipo;
    }

    public void setColumTipo(TableColumn<Cuenta, String> columTipo) {
        this.columTipo = columTipo;
    }


}
