package Controller;
import Model.User;
import Model.ViewFuntionality;
import Services.ServiceLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController extends ViewFuntionality implements Alerta{
    //Atributos
    @FXML private Button botonIniciarSesion;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoUsuario;
    @FXML private Button btnMinimize;
    @FXML private Button btnClose;
    @FXML private AnchorPane frameLogin;
    @FXML private Text botonRegistrarse;

    private User user;
    private Stage stage;
    private ServiceLogin serviceLogin = new ServiceLogin();
    private RegisterController registerController;

    //Getters y Setters
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
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Text getBotonRegistrarse() { return botonRegistrarse; }
    public void setBotonRegistrarse(Text botonRegistrarse) { this.botonRegistrarse = botonRegistrarse; }
    public RegisterController getRegisterController() { return registerController; }
    public void setRegisterController(RegisterController registerController) { this.registerController = registerController; }

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
    public void accionBotonIniciarSesion(){
        if (estanCamposVacios()){ alertaCamposIncompletos(); }
        else { alertaEmailContrasena(); }
    }
    @FXML
    public void accionRegistrarUsuario(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/registro-user.fxml"));
        Parent parent = fxmlLoader.load();
        setRegisterController(loadRegister(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getRegisterController().setVentana(loginStage);
        getRegisterController().hideStage();
        stage.setScene(scene);
        stage.setTitle("Registrarse");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    /**
     *  Método que comprueba la existencia del email ingresado, en caso contrario genera un mensaje de error.
     *  Luego, verifica la contraseña ingresada con la de la base de datos.
     *  De no ser asi, tambien genera un mensaje de error.
     */
    private void alertaEmailContrasena(){
        if (!alertaEmail()){ alertaEmailInexistente(); }
        else{
            if (!alertaContrasena()){ alertaContrasenaInvalida(); }
            else{ setUser(new User(obtenerEmail(), obtenerContrasena())); }
        }
    }

    private boolean estanCamposVacios(){ return obtenerEmail().isEmpty() || obtenerContrasena().isEmpty(); }

    private boolean alertaContrasena(){ return compararContrasenas(); }

    private boolean alertaEmail(){ return !existeUserEmail(obtenerEmail()) ? false : true; }

    @Override
    public boolean alertaCamposIncompletos() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los campos!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    @Override
    public void alertaEmailInexistente() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡El email no existe!\nPor favor, ingrese un email correcto.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    @Override
    public void alertaContrasenaInvalida() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡La contraseña es incorrecta!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    /**Metodo en el cual se obtiene el controlador de la vista registro-user**/
    private RegisterController loadRegister(RegisterController controller){ return controller; }
}
