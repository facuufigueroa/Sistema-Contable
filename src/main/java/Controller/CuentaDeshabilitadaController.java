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

import java.net.URL;
import java.util.ResourceBundle;

public class CuentaDeshabilitadaController extends ViewFuntionality implements Initializable {

    @FXML private Button btnMinimize;
    @FXML private Button btnClose;
    @FXML private Button btnVolver;

    @FXML private Button btnAgregarCuenta;
    @FXML private TableView<Cuenta> tableCuentasD;
    @FXML private TableColumn<Cuenta,String> columName;
    @FXML private TableColumn<Cuenta,String> columCodigo;
    @FXML private TableColumn<Cuenta,String> columRecibeSaldo;
    @FXML private TableColumn<Cuenta,String> columTipo;

    private CuentaDeshabilitadaController cuentaDeshabilitadaController;

    private ServicePDC servicePDC = new ServicePDC();

    private ObservableList<Cuenta> obCuentas = FXCollections.observableArrayList(servicePDC.listCuentasDeshabilitadas());

    public void listarCuentas(){
        columName.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("nombre"));
        columCodigo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("codigo"));
        columRecibeSaldo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("recibe_saldo"));
        columTipo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("tipo"));
        tableCuentasD.setItems(obCuentas);
    }

    @FXML
    public void accionVolver(){

    }
    @FXML
    public void accionHabilitarCuenta(){

    }












    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarCuentas();
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

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }

    public Button getBtnAgregarCuenta() {
        return btnAgregarCuenta;
    }

    public void setBtnAgregarCuenta(Button btnAgregarCuenta) {
        this.btnAgregarCuenta = btnAgregarCuenta;
    }

    public TableView<Cuenta> getTableCuentasD() {
        return tableCuentasD;
    }

    public void setTableCuentasD(TableView<Cuenta> tableCuentasD) {
        this.tableCuentasD = tableCuentasD;
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

    public CuentaDeshabilitadaController getCuentaDeshabilitadaController() {
        return cuentaDeshabilitadaController;
    }

    public void setCuentaDeshabilitadaController(CuentaDeshabilitadaController cuentaDeshabilitadaController) {
        this.cuentaDeshabilitadaController = cuentaDeshabilitadaController;
    }




}
