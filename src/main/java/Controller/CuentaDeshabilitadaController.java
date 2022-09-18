package Controller;

import Model.Alerta;
import Model.Cuenta;
import Model.ViewFuntionality;
import Services.ServicePDC;
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
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    private CuentaController cuentaController;

    public void listarCuentasDeshabilitadas(){
        ObservableList<Cuenta> obCuentas = FXCollections.observableArrayList(servicePDC.listCuentasDeshabilitadas());
        columName.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("nombre"));
        columCodigo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("codigo"));
        columRecibeSaldo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("recibe_saldo"));
        columTipo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("tipo"));
        tableCuentasD.setItems(obCuentas);
    }

    @FXML
    public void accionHabilitarCuenta(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Esta ?");
        alert.setHeaderText("Confirmación de habilitacion de cuenta");
        alert.setTitle("No seleccion de cuenta");

        alert.initStyle(StageStyle.TRANSPARENT);
        List<Cuenta> filaSeleccionada = tableCuentasD.getSelectionModel().getSelectedItems();
        if(filaSeleccionada.size() == 1 ){
            alert.setContentText("¿Está seguro de habilitar la cuenta: " + filaSeleccionada.get(0).nombre + " ?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    servicePDC.habilitarCuenta(accionTablaCuentasD());
                    listarCuentasDeshabilitadas();
                }
            });
        }
        else{
            alertaFilaNoSeleccionada();
        }

    }

    @FXML
    public String accionTablaCuentasD(){
        String codigo_cuenta = "";
        try{
            codigo_cuenta = String.valueOf(tableCuentasD.getSelectionModel().getSelectedItem().codigo);
        }catch (NullPointerException e){
        }
        return codigo_cuenta;
    }

    public void alertaFilaNoSeleccionada() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Cuenta NO seleccionada");
        alert.setContentText("¡No selecciono ninguna fila de la tabla cuentas, para habilitar seleccione una fila!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/plan-de-cuenta.fxml"));
        Parent parent = fxmlLoader.load();
        setCuentaController(loadRegister(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getCuentaController().setVentana(loginStage);
        getCuentaController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void hideStage(){ getVentana().hide(); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarCuentasDeshabilitadas();
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

    public CuentaController getCuentaController() {
        return cuentaController;
    }

    public void setCuentaController(CuentaController cuentaController) {
        this.cuentaController = cuentaController;
    }

    private CuentaController loadRegister(CuentaController controllerCuentaH){ return controllerCuentaH; }
}
