package Controller;
import Model.*;
import Services.ServiceRoles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends ViewFuntionality implements Initializable {
    @FXML
    private Button btnPlanDeCuenta;

    @FXML
    private Button btnVerAsientos;

    @FXML
    private Button btnNuevoAsiento;

    @FXML
    private Button btnLibroMayor;

    @FXML
    private Button btnCrearUsuario;

    @FXML
    private TextField  txtUsuarioEnSesion;
    @FXML
    private Label campoTexto;

    private Roles roles = null;
    private User usuario;

    private RegisterController registerController;

    private static ServiceRoles serviceRoles = new ServiceRoles();


    private CuentaController cuentaController;

    private AsientoController asientoController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void accionRegistrarUsuario(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/registro-user.fxml"));
        Parent parent = fxmlLoader.load();
        setRegisterController(loadRegister(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getRegisterController().setVentana(loginStage);
        getRegisterController().hideStage();
        stage.setScene(scene);
        stage.setTitle("Registrar Usuario");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    /**Metodo en el cual se obtiene el controlador de la vista registro-user**/
    private RegisterController loadRegister(RegisterController controller){ return controller; }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }


    public void permisos(){ getRoles().permisos(this); }
    public void nombreCompletoUsuario(){
        getTxtUsuarioEnSesion().setText(getRoles().nombre()
                                        + " "
                                        + getRoles().apellido()
                                        + " "
                                        + getRoles().getRol()
        );
    }
    private void cambiarTexto(Label campoTexto, String texto){ campoTexto.setText(texto); }
    private void rellenarCampos(){
        cambiarTexto(getCampoTexto(), getRoles().nombre()
                                            + " "
                                            + getRoles().apellido()
                                            + " - "
                                            + getRoles().getRol()
        );
    }
    public void cargarDatos(User user){
        setUsuario(new User(user.getNombre(), user.getApellido(), user.getEmail(), user.getContrasena()));
        obtenerRolUsuario(getUsuario());
        permisos();
        rellenarCampos();
        nombreCompletoUsuario();
    }

    private void obtenerRolUsuario(User user) {
        String rol = getServiceRoles().obtenerRolUsuarioPorEmail(user.getEmail());
        setRoles(getServiceRoles().roles(rol));
        getRoles().setUser(user);
    }

   private CuentaController loadPlanDeCuenta(CuentaController controller){ return controller; }

    @FXML
    public void accionPlanDeCuenta(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/plan-de-cuenta.fxml"));
        Parent parent = fxmlLoader.load();
        setCuentaController(loadPlanDeCuenta(fxmlLoader.getController()));
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

    private AsientoController loadCrearAsiento(AsientoController asientoController){ return asientoController; }

    @FXML
    public void accionNuevoAsiento(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/registro-asientos.fxml"));
        Parent parent = fxmlLoader.load();
        setAsientoController(loadCrearAsiento(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getAsientoController().setVentana(loginStage);
        getAsientoController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public void setRegisterController(RegisterController registerController) { this.registerController = registerController; }
    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }
    public static ServiceRoles getServiceRoles() { return serviceRoles; }
    public Label getCampoTexto() { return campoTexto; }
    public void setCampoTexto(Label campoTexto) { this.campoTexto = campoTexto; }
    public static void setServiceRoles(ServiceRoles serviceRoles) { MainController.serviceRoles = serviceRoles; }

    public CuentaController getCuentaController() {
        return cuentaController;
    }

    public void setCuentaController(CuentaController cuentaController) {
        this.cuentaController = cuentaController;

    }

    public Button getBtnPlanDeCuenta() {
        return btnPlanDeCuenta;
    }

    public void setBtnPlanDeCuenta(Button btnPlanDeCuenta) {
        this.btnPlanDeCuenta = btnPlanDeCuenta;
    }

    public Button getBtnVerAsientos() {
        return btnVerAsientos;
    }

    public void setBtnVerAsientos(Button btnVerAsientos) {
        this.btnVerAsientos = btnVerAsientos;
    }

    public Button getBtnNuevoAsiento() {
        return btnNuevoAsiento;
    }

    public void setBtnNuevoAsiento(Button btnNuevoAsiento) {
        this.btnNuevoAsiento = btnNuevoAsiento;
    }

    public Button getBtnLibroMayor() {
        return btnLibroMayor;
    }

    public void setBtnLibroMayor(Button btnLibroMayor) {
        this.btnLibroMayor = btnLibroMayor;
    }

    public Button getBtnCrearUsuario() {
        return btnCrearUsuario;
    }

    public void setBtnCrearUsuario(Button btnCrearUsuario) {
        this.btnCrearUsuario = btnCrearUsuario;
    }

    public TextField getTxtUsuarioEnSesion() {
        return txtUsuarioEnSesion;
    }

    public void setTxtUsuarioEnSesion(TextField txtUsuarioEnSesion) {
        this.txtUsuarioEnSesion = txtUsuarioEnSesion;
    }

    public Roles getRoles() { return roles; }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public AsientoController getAsientoController() {
        return asientoController;
    }

    public void setAsientoController(AsientoController asientoController) {
        this.asientoController = asientoController;
    }
}
