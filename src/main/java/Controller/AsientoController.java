package Controller;

import Model.Asiento;
import Model.ViewFuntionality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;


public class AsientoController extends ViewFuntionality implements Initializable {

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtNumeroAsiento;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private ComboBox cbbCuenta;

    @FXML
    private TextField txtMonto;

    @FXML
    private RadioButton checkDebe;

    @FXML
    private RadioButton checkHaber;

    @FXML
    private TableView tablaAsientos;

    @FXML
    private Button btnAgregarAsiento;

    @FXML
    private Button btnRegistrarAsiento;

    @FXML
    private TableColumn columFecha;

    @FXML
    private TableColumn columDescripcion;

    @FXML
    private TableColumn columCuenta;

    @FXML
    private TableColumn columnDebe;

    @FXML
    private TableColumn columHaber;

    private ObservableList<Asiento> obsAsientos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBoxCuentas();
    }
    @FXML
    public void accionAgregarAsiento(){

    }

    @FXML
    public void accionRegistrarAsiento(){

    }

    public void iniciarComboBoxCuentas(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Si", "No");
        cbbCuenta.setItems(items);
    }

    public void hideStage(){ getVentana().hide(); }

}
