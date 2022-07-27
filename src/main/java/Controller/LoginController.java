package Controller;

import Model.User;
import Services.ServiceLogin;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class LoginController implements Alerta{

    //Atributos
    @FXML private Button botonIniciarSesion;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoUsuario;
    private User user;

    private ServiceLogin serviceLogin = new ServiceLogin();
    //Getters y Setters
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
}
