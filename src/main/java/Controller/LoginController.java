package Controller;

import Services.ServiceLogin;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Locale;


public class LoginController {

    //Atributos
    @FXML
    private Button botonIniciarSesion;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoUsuario;

    private ServiceLogin serviceLogin = new ServiceLogin();
    //Getters y Setters
    public Button getBotonIniciarSesion() { return botonIniciarSesion; }
    public void setBotonIniciarSesion(Button botonIniciarSesion) { this.botonIniciarSesion = botonIniciarSesion; }
    public PasswordField getCampoContrasena() { return campoContrasena; }
    public void setCampoContrasena(PasswordField campoContrasena) { this.campoContrasena = campoContrasena; }
    public TextField getCampoUsuario() { return campoUsuario; }
    public void setCampoUsuario(TextField campoUsuario) { this.campoUsuario = campoUsuario; }

    //Metodos
    private String obtenerEmail(){ return getCampoUsuario().getText(); }
    private String obtenerContrasena(){ return getCampoContrasena().getText(); }

    public User obtenerUsuario(){
        User user = null;
        String email = obtenerEmail().toLowerCase(Locale.ROOT);
        user.setEmail(email);
        user.setPassword(obtenerContrasena());
        return user;
    }

    public boolean existeUserEmail(String email){
        if(serviceLogin.existeUser(email)){
            return true;
        }
        return false;
    }

    @FXML
    public void accionBotonIniciarSesion(){
        if (existeUserEmail(obtenerEmail())){
            System.out.println("Usuario: " + obtenerEmail() + "\nContraseña: " + obtenerContrasena());
        }
        else{
            System.out.println("No se encontro user");
        }
    }

}
