package Controller;

import Model.*;
import Services.ServiceAsiento;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

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
    private Label labelCodigoAsiento;

    @FXML
    private ComboBox cbbDebeHaber;
    @FXML
    private TableView<TablaVistaAsiento> tablaAsientos = new TableView<>();

    @FXML
    private Button btnAgregarAsiento;

    @FXML
    private Button btnRegistrarAsiento;

    @FXML
    private TableColumn columCuenta;

    @FXML
    private TableColumn columDebe;

    @FXML
    private TableColumn columHaber;

    private User u = User.getInstance();
    @FXML Button btnVolver;
    private Roles roles;

    private static User user = null;

    private User idUsuario;

    private ServicePDC serviceCuentas= new ServicePDC();

    private MainController mainController;

    private ObservableList<Asiento> obsAsientos;

    private ServiceAsiento serviceAsiento = new ServiceAsiento();

    ArrayList<TablaVistaAsiento> asientoCuentas = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBoxCuentas();
        traerNombresDeCuentas();
        txtFecha.setText(traerFechaActual());
        iniciarComboBoxDebeHaber();

    }

    @FXML
    public void accionAgregarAsiento(){
        if(verificarSiEsDebeHaber() == "Debe"){
            AsientoCuenta asientoCuenta = new AsientoCuenta(serviceAsiento.obtenerIdAsiento(), serviceAsiento.obtenerIdCuenta(String.valueOf(cbbCuenta.getValue())), Double.valueOf(txtMonto.getText()), 0);
            TablaVistaAsiento tablaVistaAsiento = new TablaVistaAsiento(serviceAsiento.obtenerNombreCuenta(asientoCuenta.getCuenta()),asientoCuenta.getDebe(),asientoCuenta.getHaber() );
            agregarATabla(tablaVistaAsiento);
        }
        else{
            AsientoCuenta asientoCuenta = new AsientoCuenta(serviceAsiento.obtenerIdAsiento(), serviceAsiento.obtenerIdCuenta(String.valueOf(cbbCuenta.getValue())), 0, Double.valueOf(txtMonto.getText()));
            TablaVistaAsiento tablaVistaAsiento = new TablaVistaAsiento(serviceAsiento.obtenerNombreCuenta(asientoCuenta.getCuenta()),asientoCuenta.getDebe(),asientoCuenta.getHaber() );
            agregarATabla(tablaVistaAsiento);
        }


    }

    @FXML
    public void accionRegistrarAsiento(){

        Asiento asiento = new Asiento(txtDescripcion.getText(), u.getId());
        serviceAsiento.insertarAsiento(asiento);


        //AsientoCuenta asientoCuenta= new AsientoCuenta(serviceAsiento.obtenerIdAsiento(), serviceAsiento.obtenerIdCuenta(cbbCuenta.getSelectionModel().toString()), cbbDebeHaber.getSelectionModel().toString(),parseDouble(columHaber.getText()),calcularSaldo());

        //serviceAsiento.insertarAsientoCuenta(asientoCuenta);

    }

    public void agregarATabla(TablaVistaAsiento asientoCuenta){

        asientoCuentas.add(asientoCuenta);
        ObservableList <TablaVistaAsiento> asientoCuentaObservableList = FXCollections.observableArrayList(asientoCuentas);


        columCuenta.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, String>("nombreCuenta"));
        columDebe.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, Double>("debe"));
        columHaber.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, Double>("haber"));


        tablaAsientos.setItems(asientoCuentaObservableList);
    }

    public boolean verificarCamposVacios(){
        return cbbCuenta.getSelectionModel().isEmpty() &&
                cbbDebeHaber.getSelectionModel().isEmpty() &&
                txtMonto.getText().isEmpty();
    }
    public String verificarSiEsDebeHaber(){
        if(cbbDebeHaber.getValue().equals("Debe")){
            return "Debe";
        }
        return "Haber";
    }




    @FXML
    public void accionDebeHaber(){

    }

    @FXML
    public String traerFechaActual(){
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaString = String.valueOf(dia+"/"+mes+"/"+año);

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
        cbbDebeHaber.setItems(items);
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
