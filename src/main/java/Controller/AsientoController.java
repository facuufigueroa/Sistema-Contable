package Controller;
import Model.*;
import Model.Alerta;
import Services.ServiceAsiento;
import Services.ServiceCalcularSaldoCuenta;
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
import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;

public class AsientoController extends ViewFuntionality implements Initializable {

    @FXML private Button btnCancelar;
    @FXML
    private Button btnBorrarAsiento;
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
    private Button btnVerCuentas;
    @FXML
    private TableColumn columCuenta;

    @FXML
    private TableColumn columDebe;

    @FXML
    private TableColumn columHaber;

    private User u = User.getInstance();
    @FXML Button btnVolver;

    private static User user = null;

    private User idUsuario;

    private ServicePDC serviceCuentas= new ServicePDC();

    private ServiceCalcularSaldoCuenta serviceCalcularSaldo = new ServiceCalcularSaldoCuenta();

    private MainController mainController;

    private ObservableList<Asiento> obsAsientos;

    private ServiceAsiento serviceAsiento = new ServiceAsiento();

    ArrayList<TablaVistaAsiento> asientoCuentas = new ArrayList<>();

    ArrayList<String> cuentasActualizadas = new ArrayList<>(serviceCuentas.traerNombreCuentas());

    private final LocalDate fechaActual = LocalDate.now();

    private VerCuentasController verCuentasController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBoxCuentas();
        txtFecha.setText(CambiarFecha.mostrarFecha(fechaActual));
        iniciarComboBoxDebeHaber();
        integerTextField(txtMonto);
    }

    /*Es necesario el if para evaluar las cuentas en caso que sean del tipo resultado y analizar si pertencen al debe o haber
    ,si todo este correcto, agregará la cuenta a la tabla*/
    @FXML
    public void accionAgregarAsiento(){

        if(!verificarCamposVacios()) {
            String tipoCuenta =  serviceAsiento.obtenerTipoDeCuenta((String) cbbCuenta.getValue());
            agregarAsiento();
            borrarCuentaUsada((String) cbbCuenta.getValue());
            restaurarCampos(cuentasActualizadas);
        }
        else{
            Alerta.alertaCamposIncompletosOperacion();
            }
    }

    public void evaluarTipoResultado(String nombre){
        if(nombre.equals("R+")){
            cbbDebeHaber.getSelectionModel().select("Haber");
            cbbDebeHaber.setDisable(true);
        } else if (nombre.equals("R-")) {
            cbbDebeHaber.getSelectionModel().select("Debe");
            cbbDebeHaber.setDisable(true);
        }else{ cbbDebeHaber.setDisable(false); }

    }
    @FXML
    public void accionCbbCuenta(){
        if(!cbbCuenta.getSelectionModel().isEmpty()){
            String seleccion = (String) cbbCuenta.getValue();
            String tipoCuenta = serviceAsiento.obtenerTipoDeCuenta(seleccion);
            evaluarTipoResultado(tipoCuenta);
        }
    }


    public void agregarAsiento(){
        AsientoCuenta asientoCuenta = new AsientoCuenta();
        if (verificarSiEsDebeHaber() == "Debe") {
            asientoCuenta.setAsiento(serviceAsiento.obtenerIdAsiento());
            asientoCuenta.setCuenta(serviceAsiento.obtenerIdCuenta(String.valueOf(cbbCuenta.getValue())));
            asientoCuenta.setDebe(Double.valueOf(txtMonto.getText()));
            asientoCuenta.setHaber(0);
            asientoCuenta.setSaldo(Double.valueOf(txtMonto.getText()));
            TablaVistaAsiento tablaVistaAsiento = new TablaVistaAsiento(serviceAsiento.obtenerNombreCuenta(asientoCuenta.getCuenta()), String.valueOf(asientoCuenta.getDebe()), "", asientoCuenta.getSaldo());
            agregarATabla(tablaVistaAsiento);
        } else {
            asientoCuenta.setAsiento(serviceAsiento.obtenerIdAsiento());
            asientoCuenta.setCuenta(serviceAsiento.obtenerIdCuenta(String.valueOf(cbbCuenta.getValue())));
            asientoCuenta.setDebe(0);
            asientoCuenta.setHaber(Double.valueOf(txtMonto.getText()));
            asientoCuenta.setSaldo(Double.valueOf(txtMonto.getText()));
            TablaVistaAsiento tablaVistaAsiento = new TablaVistaAsiento(
                     "                                                  " + serviceAsiento.obtenerNombreCuenta(asientoCuenta.getCuenta())
                    , ""
                    , String.valueOf(asientoCuenta.getHaber())
                    , asientoCuenta.getSaldo()
            );
            agregarATabla(tablaVistaAsiento);
        }
    }

    public void borrarCuentaUsada(String nombre){ cuentasActualizadas.removeIf(cuenta -> cuenta.equals(nombre)); }

    public void agregarCuentaBorrada(String nombre){
        cuentasActualizadas.add(nombre);
        ObservableList<String> cbbActDespuesDeBorrado = FXCollections.observableArrayList(cuentasActualizadas);
        Collections.sort(cbbActDespuesDeBorrado);
        cbbCuenta.setItems(cbbActDespuesDeBorrado);
    }

    @FXML
    public void accionRegistrarAsiento(ActionEvent event) throws IOException {
        if(verificarVacioAntesRegistrar()) {
            comprobarAsientos(event);
        }
        else{
            Alerta.alertaCamposIncompletosRegistrarAsiento();
        }
    }
    private void comprobarAsientos(ActionEvent event) throws IOException {
        if (verificarBalance()) {
            if (cumpleSaldo()) {
                Asiento asiento = new Asiento(fechaActual, txtDescripcion.getText(), u.getId());
                serviceAsiento.insertarAsiento(asiento);
                insertarAsientoCuenta();
                Alerta.alertarAsientoRegistrado();
                if (Alerta.alertaNuevoAsiento().getResult() == ButtonType.OK) {
                    setearCamposEnVacio();
                } else {
                    accionBtnVolver(event);
                }
            }else{
                Alerta.alertaSaldo();
            }
            this.cuentasActualizadas=serviceCuentas.traerNombreCuentas();
        } else {
            Alerta.alertarAsientoNoBalanceado();
        }
    }



    private boolean cumpleSaldo() {
        for (TablaVistaAsiento tablaAsientos : asientoCuentas) {
            String nombreCuenta = tablaAsientos.getNombreCuenta().trim();
            Cuenta cuenta = new Cuenta(nombreCuenta);
            int idCuenta = serviceAsiento.obtenerIdCuenta(nombreCuenta);
            cuenta.setSaldo_actual(serviceCalcularSaldo.obtenerSaldoCuenta(idCuenta));
            cuenta.setTipo(serviceAsiento.obtenerTipoDeCuenta(nombreCuenta));
            if (!cuenta.seCumpleSaldo(obtenerSiEsDebeHaber((tablaAsientos.getDebe())), tablaAsientos.getSaldo())){
                return false;
            }
        }
        return true;
    }

    public void insertarAsientoCuenta(){
        for (TablaVistaAsiento tablaAsientos : asientoCuentas) {
            String nombreCuenta = tablaAsientos.getNombreCuenta().trim();
            Cuenta cuenta = new Cuenta(nombreCuenta);
            int idCuenta = serviceAsiento.obtenerIdCuenta(nombreCuenta);
            cuenta.setSaldo_actual(serviceCalcularSaldo.obtenerSaldoCuenta(idCuenta));
            cuenta.setTipo(serviceAsiento.obtenerTipoDeCuenta(nombreCuenta));
            cuenta.verificarTipoCuenta(obtenerSiEsDebeHaber((tablaAsientos.getDebe())), tablaAsientos.getSaldo());
            serviceCalcularSaldo.actualizarSaldoCuenta(serviceAsiento.obtenerIdCuenta(cuenta.getNombre()), cuenta.getSaldo_actual());
            AsientoCuenta asientoCuenta = new AsientoCuenta(serviceAsiento.obtenerIdAsiento(), serviceAsiento.obtenerIdCuenta(nombreCuenta), conversionDebeHaber(tablaAsientos.getDebe()),conversionDebeHaber(tablaAsientos.getHaber()),serviceCalcularSaldo.obtenerSaldoCuenta(idCuenta));
            serviceAsiento.insertarAsientoCuenta(asientoCuenta);
        }

    }
    public String obtenerSiEsDebeHaber(String debe){
        return (debe.equals("")) ? "Haber" : "Debe";
    }

    public double conversionDebeHaber(String debeHaber){
        if (debeHaber == "") {
            return 0;
        }
        return Double.valueOf(debeHaber);
    }

    public boolean verificarVacioAntesRegistrar(){
        return !txtDescripcion.getText().isEmpty() && asientoCuentas.size() > 0;
    }

    public void agregarATabla(TablaVistaAsiento asientoCuenta){

            asientoCuentas.add(asientoCuenta);
            ObservableList<TablaVistaAsiento> asientoCuentaObservableList = FXCollections.observableArrayList(asientoCuentas);
            columCuenta.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, String>("nombreCuenta"));
            columDebe.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, String>("debe"));
            columHaber.setCellValueFactory(new PropertyValueFactory<TablaVistaAsiento, String>("haber"));
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
        return null;
    }

    @FXML
    public void accionBtnCancelar() {
        if(Alerta.alertarCancelar().getResult() == ButtonType.OK){
            setearCamposEnVacio();
        }
    }

    @FXML
    public void accionBorrarAsiento() {
        if (asientoCuentas.size() > 0){
            TablaVistaAsiento cuenta = tablaAsientos.getItems().get(asientoCuentas.size()-1);
            agregarCuentaBorrada(cuenta.getNombreCuenta().trim());
            //actualizarNombreCuentas(cuentasActualizadas);
            asientoCuentas.remove(asientoCuentas.size()-1);
            ObservableList<TablaVistaAsiento> asientoCuentaObservableList = FXCollections.observableArrayList(asientoCuentas);
            tablaAsientos.setItems(asientoCuentaObservableList);
        }
    }


    public double obtenerSaldoTotal(){
        double debe=0;
        double haber =0;
        double saldo;
        for(TablaVistaAsiento asientos: asientoCuentas){
            if(conversionDebeHaber(asientos.getHaber()) == 0){
                debe+=conversionDebeHaber(asientos.getDebe());
            }
            haber+= conversionDebeHaber(asientos.getHaber());
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
        iniciarComboBoxCuentas();
        iniciarComboBoxDebeHaber();
        tablaAsientos.setItems(null);
        this.asientoCuentas= new ArrayList<>();
    }

    public void restaurarCampos(ArrayList<String> lista){
        txtMonto.setText("");
        cbbCuenta.setItems(null);
        cbbDebeHaber.setItems(null);
        actualizarNombreCuentas(lista);
        iniciarComboBoxDebeHaber();
    }

    public static void integerTextField(TextField txtMonto) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d*\\.?\\d{0,2}$")) {
                return change;
            }
            return null;
        };
        txtMonto.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(), null, integerFilter));
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


    public void iniciarComboBoxDebeHaber(){
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Debe","Haber");
        cbbDebeHaber.setItems(items);
    }

    public void iniciarComboBoxCuentas(){
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll(serviceCuentas.traerNombreCuentas());
        cbbCuenta.setItems(cuentas);
    }
    public void actualizarNombreCuentas(ArrayList<String> lista){
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll(lista);
        Collections.sort(cuentas);
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

    @FXML
    public void accionVerPlanDeCuentas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/ver-cuentas.fxml"));
        Parent parent = fxmlLoader.load();
        setVerCuentasController(loadPlanDeCuenta(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getVerCuentasController().setVentana(loginStage);
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


    public User getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(User idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Button getBtnBorrarAsiento() { return btnBorrarAsiento;}

    public VerCuentasController getVerCuentasController() {
        return verCuentasController;
    }

    public void setVerCuentasController(VerCuentasController verCuentasController) {
        this.verCuentasController = verCuentasController;
    }

    private VerCuentasController loadPlanDeCuenta(VerCuentasController controller){ return controller; }
}
