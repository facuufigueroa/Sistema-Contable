package Controller;

import Model.Roles;
import Model.ViewFuntionality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends ViewFuntionality implements Initializable {

    @FXML
    private Button btnPlanDeCuenta;

    @FXML
    private Button btnVerAsientos;

    @FXML
    private Button btnNuevoAsiento;

    @FXML
    private Button btnLibroMayor;

    @FXML
    private Button btnCrearUsuario;

    @FXML
    private TextField  txtUsuarioEnSesion;

    private Roles roles;

    private RegisterController registerController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void accionRegistrarUsuario(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/registro-user.fxml"));
        Parent parent = fxmlLoader.load();
        setRegisterController(loadRegister(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getRegisterController().setVentana(loginStage);
        getRegisterController().hideStage();
        stage.setScene(scene);
        stage.setTitle("Registrar Usuario");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }

    /**Metodo en el cual se obtiene el controlador de la vista registro-user**/
    private RegisterController loadRegister(RegisterController controller){ return controller; }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }

    public Button getBtnPlanDeCuenta() {
        return btnPlanDeCuenta;
    }

    public void setBtnPlanDeCuenta(Button btnPlanDeCuenta) {
        this.btnPlanDeCuenta = btnPlanDeCuenta;
    }

    public Button getBtnVerAsientos() {
        return btnVerAsientos;
    }

    public void setBtnVerAsientos(Button btnVerAsientos) {
        this.btnVerAsientos = btnVerAsientos;
    }

    public Button getBtnNuevoAsiento() {
        return btnNuevoAsiento;
    }

    public void setBtnNuevoAsiento(Button btnNuevoAsiento) {
        this.btnNuevoAsiento = btnNuevoAsiento;
    }

    public Button getBtnLibroMayor() {
        return btnLibroMayor;
    }

    public void setBtnLibroMayor(Button btnLibroMayor) {
        this.btnLibroMayor = btnLibroMayor;
    }

    public Button getBtnCrearUsuario() {
        return btnCrearUsuario;
    }

    public void setBtnCrearUsuario(Button btnCrearUsuario) {
        this.btnCrearUsuario = btnCrearUsuario;
    }

    public TextField getTxtUsuarioEnSesion() {
        return txtUsuarioEnSesion;
    }

    public void setTxtUsuarioEnSesion(TextField txtUsuarioEnSesion) {
        this.txtUsuarioEnSesion = txtUsuarioEnSesion;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }
}
