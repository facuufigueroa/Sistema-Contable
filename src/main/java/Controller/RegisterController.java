package Controller;
import Model.User;
import Model.ViewFuntionality;
import Services.ServiceRegister;
import com.administrativos.sistema.utilidades.Alerta;
import com.administrativos.sistema.utilidades.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class RegisterController extends ViewFuntionality{
    @FXML private TextField campoNombre;
    @FXML private TextField campoApellido;
    @FXML private TextField campoEmail;
    @FXML private TextField campoContrasena;
    @FXML private Button botonRegistrarse;
    @FXML private Button buttonMin;
    @FXML private Button buttonClose;
    private RegisterController controller;
    private ServiceRegister serviceRegister = new ServiceRegister();

    public Button getButtonMin() { return buttonMin; }
    public Button getButtonClose() { return buttonClose ;}
    public TextField getCampoNombre() { return campoNombre; }
    public TextField getCampoApellido() { return campoApellido; }
    public TextField getCampoEmail() { return campoEmail; }
    public TextField getCampoContrasena() { return campoContrasena; }
    public RegisterController getController() { return controller; }
    public void setController(RegisterController controller) { this.controller = controller; }
    public ServiceRegister getServiceRegister() { return serviceRegister; }

    @FXML
    public void actionRegister(ActionEvent event) throws SQLException {
        if (!estanCamposVacios() && !getServiceRegister().existeUser(Utilidades.obtenerValor(getCampoEmail()))){
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

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }
    private void comprobarCampoVacionOEmailExistente(){
        if (estanCamposVacios()){   Alerta.alertaCamposIncompletos(); }
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

}
