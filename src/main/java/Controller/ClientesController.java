package Controller;

import Model.ViewFuntionality;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientesController extends ViewFuntionality implements Initializable {
    private HomeVentasController homeVentasController;

    @FXML private ComboBox<String> comboBoxCliente;
    @FXML private AnchorPane panelRegistro;
    @FXML private TableView tablaPersonas;

    @FXML private TextField txtCuit;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBox();
    }

    private void iniciarComboBox() {
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll("Persona Fisica", "Persona Juridica");
        comboBoxCliente.setItems(cuentas);
    }
    private void accionElegirPersona(){ //Si selecciona una persona se carga el panel con los datos de dicha persona
        String tipoPersona = comboBoxCliente.getValue();
        if (tipoPersona.equals("Persona Fisica")){
            //setear panel persona fisica
        }else{
            //seter panel persona juridica
        }
    }

    @FXML
    private void accionGuardarPersona(){
        //Si dni == null => obtenerPersonaJuridica
        //Si Razon social == null obtenerPersonaFisica
        //Sino error
    }

    private void obtenerPersonaFisicar(){ //TODO accion guardar persona

    }
    private void obtenerPersonaJuridica(){ //TODO accion guardar persona

    }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(loadMenuVenta(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeVentasController().setVentana(homeStage);
        getHomeVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    private HomeVentasController loadMenuVenta(HomeVentasController menuVentaseController){ return menuVentaseController; }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }


    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }


}
