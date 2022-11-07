package Controller;
import Model.*;
import Model.Alerta;
import Services.Service;
import Services.ServiceLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends ViewFuntionality{
    //Atributos
    @FXML private Button botonIniciarSesion;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoUsuario;
    @FXML private Button btnMinimize;
    @FXML private Button btnClose;
    @FXML private AnchorPane frameLogin;
    @FXML private Text botonRegistrarse;

    private Service service = new Service();


    private HomeController homeController;

    public LoginController(){}

    private Stage stage;

    private User u = User.getInstance();

    private static ServiceLogin serviceLogin = new ServiceLogin();
    private RegisterController registerController;


    //Metodos


    private String obtenerEmail(){ return getCampoUsuario().getText(); }
    private String obtenerContrasena(){ return getCampoContrasena().getText(); }

    public boolean existeUserEmail(String email){
        if(serviceLogin.existeUser(email)){
            return true;
        }
        return false;
    }
    public boolean compararContrasenas(){ return serviceLogin.coincidenContrasenas(obtenerEmail(), obtenerContrasena()); }

    @FXML
    public void accionBotonIniciarSesion(ActionEvent event) throws IOException {
        if (estanCamposVacios()){ Alerta.alertaCamposIncompletos();
        }
        else { alertaEmailContrasena(event); }

    }


    private void loadHomePrincipal(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas-asientos.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeController(loadHome(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeController().setVentana(homeStage);
        getHomeController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    private HomeController loadHome(HomeController homeController){ return homeController; }


    /**
     *  Método que comprueba la existencia del email ingresado, en caso contrario genera un mensaje de error.
     *  Luego, verifica la contraseña ingresada con la de la base de datos.
     *  De no ser asi, tambien genera un mensaje de error.
     */


    private boolean estaCampoVacio(TextField campo) {
        return campo.getText().isEmpty();
    }

    private void alertaEmailContrasena(ActionEvent event) throws IOException {
        if (!alertaEmail()){ Alerta.alertaEmailInexistente(); }
        else{
            if (!alertaContrasena()){ Alerta.alertaContrasenaInvalida(); }
            else{
                User userNew = service.obtenerUsuarioPorEmail(campoUsuario.getText());
                u.setId(userNew.getId());
                u.setNombre(userNew.getNombre());
                u.setEmail(userNew.getEmail());
                u.setContrasena(userNew.getContrasena());
                u.setApellido(userNew.getApellido());
                loadHomePrincipal(event);
            }
        }
    }

    private boolean estanCamposVacios(){ return obtenerEmail().isEmpty() || obtenerContrasena().isEmpty(); }


    private boolean alertaContrasena(){ return compararContrasenas(); }

    private boolean alertaEmail(){ return !existeUserEmail(obtenerEmail()) ? false : true; }

    public void hideStage(){ getVentana().hide(); }

    public static ServiceLogin getServiceLogin() {
        return serviceLogin;
    }

    public static void setServiceLogin(ServiceLogin serviceLogin) {
        LoginController.serviceLogin = serviceLogin;
    }

    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public AnchorPane getFrameLogin() {
        return frameLogin;
    }
    public void setFrameLogin(AnchorPane frameLogin) {
        this.frameLogin = frameLogin;
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
    public Button getBotonIniciarSesion() { return botonIniciarSesion; }
    public void setBotonIniciarSesion(Button botonIniciarSesion) { this.botonIniciarSesion = botonIniciarSesion; }
    public PasswordField getCampoContrasena() { return campoContrasena; }
    public void setCampoContrasena(PasswordField campoContrasena) { this.campoContrasena = campoContrasena; }
    public TextField getCampoUsuario() { return campoUsuario; }
    public void setCampoUsuario(TextField campoUsuario) { this.campoUsuario = campoUsuario; }

    public Text getBotonRegistrarse() { return botonRegistrarse; }
    public void setBotonRegistrarse(Text botonRegistrarse) { this.botonRegistrarse = botonRegistrarse; }
    public RegisterController getRegisterController() { return registerController; }
    public void setRegisterController(RegisterController registerController) { this.registerController = registerController; }

    /* Metodos get y set agregados para ventas*/

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
