package Controller;

import Model.*;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
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
    private ComboBox cbbDebeHaber;
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

    @FXML Button btnVolver;
    private Roles roles;

    private static User user = null;

    private User idUsuario;

    private ServicePDC serviceCuentas= new ServicePDC();

    private MainController mainController;

    private ObservableList<Asiento> obsAsientos;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBoxCuentas();
        traerNombresDeCuentas();
        txtFecha.setText(traerFechaActual());
        iniciarComboBoxDebeHaber();

    }

    @FXML
    public void accionAgregarAsiento(){

    }

    @FXML
    public void accionRegistrarAsiento(){

    }

    @FXML
    public void accionDebeHaber(){

    }

    @FXML
    public String traerFechaActual(){
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaString = String.valueOf(año+"/"+mes+"/"+dia);
        return fechaString;
    }

    public void iniciarComboBoxCuentas(){
        ObservableList<Cuenta> items = FXCollections.observableArrayList();
        items.addAll(serviceCuentas.listCuentasHabilitadas());
        cbbCuenta.setItems(items);
    }

    public void iniciarComboBoxDebeHaber(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Debe","Haber");
        cbbCuenta.setItems(items);
    }

    public void traerNombresDeCuentas(){
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll(serviceCuentas.traerNombreCuentas());
        cbbCuenta.setItems(cuentas);
    }

    public void hideStage(){ getVentana().hide(); }


    @FXML
    public void accionBtnVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/home-principal.fxml"));
        Parent parent = fxmlLoader.load();
        setMainController(loadVolver(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getMainController().setVentana(mainStage);
        getMainController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    private MainController loadVolver(MainController mainController){ return mainController; }

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Roles getRoles() { return this.roles; }
    public void setRoles(Roles roles) { this.roles = roles; }

    public static void setUser(User user) {
        AsientoController.user = user;
    }

    public User getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(User idUsuario) {
        this.idUsuario = idUsuario;
    }
}
