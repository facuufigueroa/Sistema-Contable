package Controller;

import Model.Cuenta;
import Model.ViewFuntionality;
import Services.ServicePDC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class VerCuentasController extends ViewFuntionality implements Initializable {

    @FXML
    private TableView<Cuenta> tableCuentas;

    @FXML private TableColumn<Cuenta,String> columName;

    @FXML private TableColumn<Cuenta,String> columSaldo;

    @FXML private TableColumn<Cuenta,String> columCodigo;

    @FXML private TableColumn<Cuenta,String> columRecibeSaldo;

    @FXML private TableColumn<Cuenta,String> columTipo;

    private ServicePDC servicePDC = new ServicePDC();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarCuentasHabilitadas();
    }

    public void listarCuentasHabilitadas(){
        ObservableList<Cuenta> obCuentas = FXCollections.observableArrayList(servicePDC.listCuentasHabilitadas());
        columName.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("nombre"));
        columCodigo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("codigo"));
        columRecibeSaldo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("recibe_saldo"));
        columTipo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("tipo"));
        columSaldo.setCellValueFactory(new PropertyValueFactory<Cuenta, String>("saldo_actual"));
        tableCuentas.setItems(obCuentas);
    }
}
