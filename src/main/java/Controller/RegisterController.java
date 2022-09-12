package Controller;

import Model.*;
import Model.Alerta;
import Services.ServiceRegister;

import Model.ViewFuntionality;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController extends ViewFuntionality implements Initializable {
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoEmail;
    @FXML private TextField campoContrasena;
    @FXML private Button botonRegistrarse;
    @FXML private Button buttonMin;
    @FXML private Button buttonClose;
    @FXML
    private CheckBox checkRolAdmin;
    @FXML
    private CheckBox checkRolUser;

    private RegisterController controller;
    private ServiceRegister serviceRegister = new ServiceRegister();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getBotonRegistrarse().setDisable(false);
    }

    public Button getButtonMin() { return buttonMin; }
    public Button getButtonClose() { return buttonClose ;}
    public Button getBotonRegistrarse() { return botonRegistrarse; }
    public TextField getCampoNombre() { return campoNombre; }
    public TextField getCampoApellido() { return campoApellido; }
    public TextField getCampoEmail() { return campoEmail; }
    public TextField getCampoContrasena() { return campoContrasena; }
    public RegisterController getController() { return controller; }
    public void setController(RegisterController controller) { this.controller = controller; }
    public ServiceRegister getServiceRegister() { return serviceRegister; }

    @FXML
    public void actionRegister(ActionEvent event) throws SQLException {
        if (noEstanCamposVaciosYExisteEmail()){
            getServiceRegister().insertarUsuario(obtenerFormulario());
            super.actionCloseStage(event);
            showStage();
        }else{ comprobarCampoVacionOEmailExistente(); }
    }

    @Override
    public void actionCloseStage(ActionEvent event) {
        super.actionCloseStage(event);
        showStage();
    }
    @FXML
    private boolean datoEsTexto(){
        if(Validacion.esTexto(getCampoNombre().getText()) && Validacion.esTexto(getCampoApellido().getText())){
            getBotonRegistrarse().setDisable(false);
            return true;
        }
        getBotonRegistrarse().setDisable(true);
        return false;
    }
    @FXML
    private boolean datoEsEmail(){
        if (Validacion.validarEmail(getCampoEmail().getText())){
            getBotonRegistrarse().setDisable(false);
            return true;
        }
        getBotonRegistrarse().setDisable(true);
        return false;
    }
    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }

    private boolean noEstanCamposVaciosYExisteEmail(){
        return !estanCamposVacios() && !getServiceRegister().existeUser(Utilidades.obtenerValor(getCampoEmail()));
    }
    private void comprobarCampoVacionOEmailExistente(){
        if (estanCamposVacios()){ Alerta.alertaCamposIncompletos(); }
        else{ Alerta.alertaEmailYaExiste(); }
    }
    private boolean estanCamposVacios(){
        return  Utilidades.estaCampoVacio(getCampoNombre()) || Utilidades.estaCampoVacio(getCampoApellido())
                || Utilidades.estaCampoVacio(getCampoEmail()) || Utilidades.estaCampoVacio(getCampoContrasena());
    }
    private User obtenerFormulario(){
        String nombre = Utilidades.obtenerValor(getCampoNombre());
        String apellido = Utilidades.obtenerValor(getCampoApellido());
        String email = Utilidades.obtenerValor(getCampoEmail());
        String contrasena = Utilidades.obtenerValor(getCampoContrasena());
        return new User(  Utilidades.capitalizarTexto(nombre)
                , Utilidades.capitalizarTexto(apellido)
                , email
                , contrasena);
    }

    private boolean validarDatos(String nombre, String apellido, String email){
        if (Validacion.esTexto(nombre) && Validacion.esNumero(apellido) && Validacion.validarEmail(email)){
            getBotonRegistrarse().setDisable(false);
            return true;
        }else{
            getBotonRegistrarse().setDisable(true);
            return false;
        }
    }

    /*Metodo que trae 1 si el rol seleccionado en el checkbox es admin*/
    @FXML
    private int accionCheckRolAdmin(){
        int rolAdmin =0;
        if(checkRolAdmin.isSelected()){
            rolAdmin=1;
        }
        return rolAdmin;
    }
    /*Metodo que trae 2 si el rol seleccionado en el checkbox es usuario*/
    @FXML
    private int accionCheckRolUser() {
        int rolUser = 0;
        if (checkRolAdmin.isSelected()) {
            rolUser=2;
        }
        return rolUser;
    }



}
