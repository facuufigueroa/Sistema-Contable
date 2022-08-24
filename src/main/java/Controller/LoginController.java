package Controller;

import Model.User;
import Services.ServiceLogin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.MouseEvent;


public class LoginController  implements Alerta{

    //Atributos
    @FXML private Button botonIniciarSesion;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoUsuario;

    @FXML private Button btnMinimize;

    @FXML private Button btnClose;

    @FXML private AnchorPane frameLogin;

    private User user;

    private Stage stage;


    private ServiceLogin serviceLogin = new ServiceLogin();



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

    /* Metodos para btns de login */
    @FXML
    public void btnClose(){
        System.exit(0);
    }
    @FXML
    public void min(MouseDragEvent mouseEvent){

       Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
       
       stage.setIconified(true);
    }

    /* Metodo para ser llamada en la accion del boton
    public void btnMinimizeEjecute(Stage stage){
        try {
            FXMLLoader f = FXMLLoader.load(getClass().getResource("login-user.fxml"));
            f.setController(this);
            Parent root = f.load();
            Scene sc = new Scene(root);
            stage.setScene(sc);
            stage.setIconified(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/

}
