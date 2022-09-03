package Controller;
import Model.ViewFuntionality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController extends ViewFuntionality{
    @FXML private TextField campoUsuario;
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

    @Override
    public void actionCloseStage(ActionEvent event) {
        super.actionCloseStage(event);
        showStage();
    }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }
}
