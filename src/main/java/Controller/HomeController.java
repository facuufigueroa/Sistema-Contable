package Controller;

import Model.Alerta;
import Model.User;
import Model.ViewFuntionality;
import Services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends ViewFuntionality implements Initializable {

    @FXML
    private Button btnVentas;

    @FXML
    private Button btnAdminContable;

    @FXML
    private Label txtUsuarioLogueado;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnQuienesSomos;

    private MainController mainControllerContablidad;

    private HomeVentasController homeVentasController;

    private User u = User.getInstance();

    private Service service = new Service();

    private LoginController loginController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cambiarTexto();
        verificarRolUser();
    }
    private void verificarRolUser(){
        String rol = service.obtenerRolPorEmail(u.getEmail()).toUpperCase();
        if(rol.equals("usuario".toUpperCase())){
            deshabilitarBoton(getBtnVentas(), true);
        } else if (rol.equals("vendedor".toUpperCase())) {
            deshabilitarBoton(getBtnAdminContable(), true);
        } else if (rol.equals("jefe venta".toUpperCase())) {
            deshabilitarBoton(getBtnAdminContable(), true);
        }
    }

    private void deshabilitarBoton(Button boton, boolean deshabilitar){ boton.setDisable(deshabilitar); }

    private void loadAdminContable(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/home-principal.fxml"));
        Parent parent = fxmlLoader.load();
        setMainControllerContablidad(loadContableMain(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage adminContableStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getMainControllerContablidad().setVentana(adminContableStage);
        getMainControllerContablidad().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    private MainController loadContableMain(MainController mainController){ return mainController; }



    public void hideStage(){ getVentana().hide(); }


    @FXML
    public void accionBtnMainContabilidad(ActionEvent event) throws IOException {
        loadAdminContable(event);
    }

    private void loadVentas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(loadVentasMain(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage adminContableStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeVentasController().setVentana(adminContableStage);
        getHomeVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    public void accionBtnMainVentas(ActionEvent event) throws IOException {
        loadVentas(event);
    }


    private HomeVentasController loadVentasMain(HomeVentasController ventasController){ return ventasController; }

    private void cambiarTexto(){ getTxtUsuarioLogueado().setText(u.getNombre().toUpperCase()+" "+u.getApellido().toUpperCase()+" "+"| "+service.obtenerRolPorEmail(u.getEmail()).toUpperCase()); }

    @FXML
    public void accionBtnCerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/login-user.fxml"));
        Parent parent = fxmlLoader.load();
        setLoginController(loadLogin(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage adminContableStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getLoginController().setVentana(adminContableStage);
        getLoginController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    private LoginController loadLogin(LoginController loginController){ return loginController; }


    public Button getBtnVentas() {
        return btnVentas;
    }

    public void setBtnVentas(Button btnVentas) {
        this.btnVentas = btnVentas;
    }

    public Button getBtnAdminContable() {
        return btnAdminContable;
    }

    public void setBtnAdminContable(Button btnAdminContable) {
        this.btnAdminContable = btnAdminContable;
    }

    public Label getTxtUsuarioLogueado() {
        return txtUsuarioLogueado;
    }

    public void setTxtUsuarioLogueado(Label txtUsuarioLogueado) {
        this.txtUsuarioLogueado = txtUsuarioLogueado;
    }

    public Button getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public void setBtnCerrarSesion(Button btnCerrarSesion) {
        this.btnCerrarSesion = btnCerrarSesion;
    }

    public Button getBtnQuienesSomos() {
        return btnQuienesSomos;
    }

    public void setBtnQuienesSomos(Button btnQuienesSomos) {
        this.btnQuienesSomos = btnQuienesSomos;
    }

    public MainController getMainControllerContablidad() {
        return mainControllerContablidad;
    }

    public void setMainControllerContablidad(MainController mainControllerContablidad) {
        this.mainControllerContablidad = mainControllerContablidad;
    }

    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
