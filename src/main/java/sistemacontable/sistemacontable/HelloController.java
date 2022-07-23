package sistemacontable.sistemacontable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController{
    //Atributos
    @FXML private Button botonIniciarSesion;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoUsuario;

    //Getters y Setters
    public Button getBotonIniciarSesion() { return botonIniciarSesion; }
    public void setBotonIniciarSesion(Button botonIniciarSesion) { this.botonIniciarSesion = botonIniciarSesion; }
    public PasswordField getCampoContrasena() { return campoContrasena; }
    public void setCampoContrasena(PasswordField campoContrasena) { this.campoContrasena = campoContrasena; }
    public TextField getCampoUsuario() { return campoUsuario; }
    public void setCampoUsuario(TextField campoUsuario) { this.campoUsuario = campoUsuario; }

    //Metodos
    private String obtenerUsuario(){ return getCampoUsuario().getText(); }
    private String obtenerContrasena(){ return getCampoContrasena().getText(); }
    @FXML
    public void accionBotonIniciarSesion(){
        if (!obtenerUsuario().isEmpty() && !obtenerContrasena().isEmpty()){
            System.out.println("Usuario: " + obtenerUsuario() + "\nContraseña: " + obtenerContrasena());
        }
    }
}