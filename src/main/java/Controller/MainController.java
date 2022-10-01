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
    private Label campoTexto;

    @FXML
    private Button btnCerrarSesion;

    private Roles roles = null;
    private User usuario;

    private RegisterController registerController;

    private static ServiceRoles serviceRoles = new ServiceRoles();

    private User u = User.getInstance();

    private CuentaController cuentaController;

    private AsientoController asientoController;

    private LoginController loginController;

    public MainController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verificarRolUser();
        cambiarTexto();
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


    private void cambiarTexto(){ getCampoTexto().setText(u.getNombre().toUpperCase()+" "+u.getApellido().toUpperCase()+" "+"| "+serviceRoles.obtenerRolPorEmail(u.getEmail()).toUpperCase()); }


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
    @FXML
    public void accionBtnCerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/login-user.fxml"));
        Parent parent = fxmlLoader.load();
        setLoginController(loadLogin(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getLoginController().setVentana(loginStage);
        getLoginController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public LoginController loadLogin (LoginController controller){
        return controller;
    }

    /*Metodo para roles*/

    public void verificarRolUser(){
        if(serviceRoles.obtenerRolPorEmail(u.getEmail()).equals("usuario")){
            getBtnCrearUsuario().setDisable(true);
        }

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

    public Button getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public void setBtnCerrarSesion(Button btnCerrarSesion) {
        this.btnCerrarSesion = btnCerrarSesion;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
