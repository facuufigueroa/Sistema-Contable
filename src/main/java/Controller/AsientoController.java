package Controller;

import Model.*;
import Model.Alerta;
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
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        integerTextField(txtMonto);
    }

    @FXML
    public void accionAgregarAsiento(){
        AsientoCuenta asientoCuenta = new AsientoCuenta();
        if(!verificarCamposVacios()) {
            if (verificarSiEsDebeHaber() == "Debe") {
                asientoCuenta.setAsiento(serviceAsiento.obtenerIdAsiento());
                asientoCuenta.setCuenta(serviceAsiento.obtenerIdCuenta(String.valueOf(cbbCuenta.getValue())));
                asientoCuenta.setDebe(Double.valueOf(txtMonto.getText()));
                asientoCuenta.setHaber(0);
                asientoCuenta.setSaldo(Double.valueOf(txtMonto.getText()));
                TablaVistaAsiento tablaVistaAsiento = new TablaVistaAsiento(serviceAsiento.obtenerNombreCuenta(asientoCuenta.getCuenta()), asientoCuenta.getDebe(), asientoCuenta.getHaber(), asientoCuenta.getSaldo());
                agregarATabla(tablaVistaAsiento);

            } else {
                asientoCuenta.setAsiento(serviceAsiento.obtenerIdAsiento());
                asientoCuenta.setCuenta(serviceAsiento.obtenerIdCuenta(String.valueOf(cbbCuenta.getValue())));
                asientoCuenta.setDebe(0);
                asientoCuenta.setHaber(Double.valueOf(txtMonto.getText()));
                asientoCuenta.setSaldo(Double.valueOf(txtMonto.getText()));

                TablaVistaAsiento tablaVistaAsiento = new TablaVistaAsiento(serviceAsiento.obtenerNombreCuenta(asientoCuenta.getCuenta()), asientoCuenta.getDebe(), asientoCuenta.getHaber(), asientoCuenta.getSaldo());
                agregarATabla(tablaVistaAsiento);
            }
        }
        else{
            Alerta.alertaCamposIncompletos();
        }


    }


    @FXML
    public void accionRegistrarAsiento(ActionEvent event) throws IOException {

        if(verificarVacioAntesRegistrar()) {
            if (verificarBalance()) {
                Asiento asiento = new Asiento(txtDescripcion.getText(), u.getId());
                serviceAsiento.insertarAsiento(asiento);
                for (TablaVistaAsiento tablaAsientos : asientoCuentas) {
                    AsientoCuenta asientoCuenta = new AsientoCuenta(serviceAsiento.obtenerIdAsiento(), serviceAsiento.obtenerIdCuenta(cbbCuenta.getValue().toString()), tablaAsientos.getDebe(), tablaAsientos.getHaber(), tablaAsientos.getSaldo());
                    serviceAsiento.insertarAsientoCuenta(asientoCuenta);
                }
                Alerta.alertarAsientoRegistrado();
                if(Alerta.alertaNuevoAsiento().getResult() == ButtonType.OK){
                    setearCamposEnVacio();
                }
                else{
                    accionBtnVolver(event);
                }

            } else {
                Alerta.alertarAsientoNoRegistrado();
            }
        }
        else{
            Alerta.alertaCamposIncompletos();
        }
    }

    public boolean verificarVacioAntesRegistrar(){
        return !txtDescripcion.getText().isEmpty() && asientoCuentas.size() > 0;
    }

    public void agregarATabla(TablaVistaAsiento asientoCuenta){

            asientoCuentas.add(asientoCuenta);
            ObservableList<TablaVistaAsiento> asientoCuentaObservableList = FXCollections.observableArrayList(asientoCuentas);
            columCuenta.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, String>("nombreCuenta"));
            columDebe.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, Double>("debe"));
            columHaber.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, Double>("haber"));
            tablaAsientos.setItems(asientoCuentaObservableList);
    }

    public boolean verificarCamposVacios(){
        return cbbCuenta.getSelectionModel().isEmpty() ||
                cbbDebeHaber.getSelectionModel().isEmpty() ||
                txtMonto.getText().isEmpty();
    }

    public String verificarSiEsDebeHaber(){
        if(!verificarCamposVacios()){
            if(cbbDebeHaber.getValue().equals("Debe")){
                return "Debe";
            }
            return "Haber";
        }
        else{
            Alerta.alertarCampoDebeHaberVacio();
        }
        return null; /*Resolver este problema*/
    }




    @FXML
    public void accionDebeHaber(){

    }


    public double obtenerSaldoTotal(){
        double debe=0;
        double haber =0;
        double saldo;
        for(TablaVistaAsiento asientos: asientoCuentas){
            if(asientos.getHaber()==0){
                debe+=asientos.getDebe();
            }
            haber+=asientos.getHaber();
        }
        saldo=debe-haber;
        return saldo;
    }

    public boolean verificarBalance(){
        if(obtenerSaldoTotal() == 0){
            return true;
        }
        return false;
    }

    public void setearCamposEnVacio(){
        txtDescripcion.setText("");
        txtMonto.setText("");
        cbbCuenta.setItems(null);
        cbbDebeHaber.setItems(null);
        traerNombresDeCuentas();
        iniciarComboBoxDebeHaber();
        tablaAsientos.setItems(null);
        this.asientoCuentas= new ArrayList<>();
    }


    public static boolean datosNumericos(String monto){
        Pattern patron = Pattern.compile("(\\d+)(((.)\\d+)+)?");
        Matcher matcher = patron.matcher(monto);
        return matcher.find();
    }

    public static void integerTextField(TextField txtMonto) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*\\.?\\d{0,2}$")) {
                return change;
            }
            return null;
        };
        txtMonto.setTextFormatter(
                new TextFormatter<Double>(
                        new DoubleStringConverter(), null, integerFilter));
    }



    /*---------------------Metodos para inicializar*---------------------------------*/

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

    /*----------------------------------------------------------------------*/

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
