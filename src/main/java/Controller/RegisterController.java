package Controller;

import Model.Alerta;
import Model.User;
import Model.Utilidades;
import Model.ViewFuntionality;
import Services.ServiceRegister;

import Model.ViewFuntionality;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController extends ViewFuntionality{
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoEmail;
    @FXML private TextField campoContrasena;
    @FXML private Button botonRegistrarse;
    @FXML private Button buttonMin;
    @FXML private Button buttonClose;
    private RegisterController controller;

    public Button getButtonMin() { return buttonMin; }
    public Button getButtonClose() { return buttonClose ;}

    public RegisterController getController() { return controller; }
    public void setController(RegisterController controller) { this.controller = controller; }

    @FXML
    public void actionRegister(ActionEvent event){
        super.actionCloseStage(event);
        showStage();
    }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }

    private void comprobarCampoVacionOEmailExistente(){
        if (estanCamposVacios()){ Alerta.alertaCamposIncompletos(); }
        else{ Alerta.alertaEmailYaExiste(); }
    }
    private boolean estanCamposVacios(){
        return  Utilidades.estaCampoVacio(getCampoNombre()) || Utilidades.estaCampoVacio(getCampoApellido())
                || Utilidades.estaCampoVacio(getCampoEmail()) || Utilidades.estaCampoVacio(getCampoContrasena());
    }
    private User obtenerFormulario(){
        return new User(Utilidades.obtenerValor(getCampoNombre()),
                        Utilidades.obtenerValor(getCampoApellido()),
                        Utilidades.obtenerValor(getCampoEmail()),
                        Utilidades.obtenerValor(getCampoContrasena())
        );
    }


    public TextField getCampoNombre() {
        return campoNombre;
    }

    public TextField getCampoApellido() {
        return campoApellido;
    }

    public void setCampoApellido(TextField campoApellido) {
        this.campoApellido = campoApellido;
    }

    public TextField getCampoEmail() {
        return campoEmail;
    }

    public void setCampoEmail(TextField campoEmail) {
        this.campoEmail = campoEmail;
    }

    public TextField getCampoContrasena() {
        return campoContrasena;
    }

    public void setCampoContrasena(TextField campoContrasena) {
        this.campoContrasena = campoContrasena;
    }

    public Button getBotonRegistrarse() {
        return botonRegistrarse;
    }

    public void setBotonRegistrarse(Button botonRegistrarse) {
        this.botonRegistrarse = botonRegistrarse;
    }

    public void setButtonMin(Button buttonMin) {
        this.buttonMin = buttonMin;
    }

    public void setButtonClose(Button buttonClose) {
        this.buttonClose = buttonClose;
    }
}
