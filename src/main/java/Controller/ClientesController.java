package Controller;

import Model.Ventas.Persona;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientesController extends ViewFuntionality implements Initializable {
    private HomeVentasController homeVentasController;

    @FXML private ComboBox<String> comboBoxCliente;
    @FXML private AnchorPane panelRegistro = new AnchorPane();
    @FXML private TableView tablaPersonas;

    //Persona Fisica
    @FXML private TextField txtDni;
    @FXML private TextField txtCuit;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtTelefono;

    //Persona Juridica
    @FXML private TextField txtCuitJ;
    @FXML private TextField txtEmailJ;
    @FXML private TextField txtTelefonoJ;
    @FXML private TextField txtDireccionJ;
    @FXML private TextField txtRazonSocialJ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBox();
    }

    private void iniciarComboBox() {
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll("Persona Fisica", "Persona Juridica");
        comboBoxCliente.setItems(cuentas);
    }

    /**
     *   Paneles de registros de personas
     */
    public AnchorPane personaFisica(){
        AnchorPane fisica = new AnchorPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ventas-View/clientes/persona-fisica.fxml"));
            Parent parent = loader.load();
            fisica = (AnchorPane) parent;
            return fisica;
        }catch (IOException excepcion){ System.out.println(excepcion.getMessage()); }
        return fisica;
    }
    public AnchorPane personaJuridica(){
        AnchorPane juridica = new AnchorPane();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ventas-View/clientes/persona-juridica.fxml"));
            Parent parent = loader.load();
            juridica = (AnchorPane) parent;
            return juridica;
        }catch (IOException excepcion){ System.out.println(excepcion.getMessage()); }
        return juridica;
    }


    @FXML
    public void accionCambiarPersona(){
        String tipoPersona = comboBoxCliente.getValue();
        if (tipoPersona.equals("Persona Fisica")){
            //setPanelRegistro(personaFisica());
            getPanelRegistro().getChildren().setAll(personaFisica());
        }else if (tipoPersona.equals("Persona Juridica")){
            //setPanelRegistro(personaJuridica());
            getPanelRegistro().getChildren().setAll(personaJuridica());
        }
    }
    private void accionElegirPersona(){ //Si selecciona una persona se carga el panel con los datos de dicha persona
        String tipoPersona = comboBoxCliente.getValue();
        if (tipoPersona.equals("Persona Fisica") && comprobarPersonaFisicaNoNula()){
            //setear panel persona fisica
            Persona fisica = getPersonaFisica();

        }else if (tipoPersona.equals("Persona Juridica") && comprobarPersonaJuridicaNoNula()){
            //seter panel persona juridica
            Persona juridica = getPersonaJuridica();
        }
    }

    private boolean comprobarPersonaJuridicaNoNula() { //TODO cambiar metodo
        return true;
    }

    private boolean comprobarPersonaFisicaNoNula() {  //TODO cambiar metodo
        return true;
    }

    private Persona getPersonaFisica(){
        return new Persona(   Long.valueOf(getTxtDni().getText())
                            , getTxtCuit().getText()
                            , getTxtNombre().getText()
                            , getTxtApellido().getText()
                            , getTxtEmail().getText()
                            , getTxtDireccion().getText()
                            , getTxtTelefono().getText()
        );
    }
    private Persona getPersonaJuridica(){ //cuit, razonSocial, email, direccion, telefono
        return new Persona(   getTxtCuitJ().getText()
                            , getTxtRazonSocialJ().getText()
                            , getTxtEmailJ().getText()
                            , getTxtDireccionJ().getText()
                            , getTxtTelefonoJ().getText()
        );
    }

    @FXML
    private void accionGuardarPersona(){
        //Si dni == null => obtenerPersonaJuridica
        //Si Razon social == null obtenerPersonaFisica
        //Sino error
    }

    private void obtenerPersonaFisica(){ //TODO accion guardar persona

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

    public ComboBox<String> getComboBoxCliente() {
        return comboBoxCliente;
    }

    public AnchorPane getPanelRegistro() {
        return panelRegistro;
    }

    public TableView getTablaPersonas() {
        return tablaPersonas;
    }
    public void setPanelRegistro(AnchorPane panelRegistro) {
        this.panelRegistro = panelRegistro;
    }

    public TextField getTxtDni() {
        return txtDni;
    }

    public TextField getTxtCuit() {
        return txtCuit;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtApellido() {
        return txtApellido;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public TextField getTxtDireccion() {
        return txtDireccion;
    }

    public TextField getTxtTelefono() {
        return txtTelefono;
    }

    public TextField getTxtCuitJ() {
        return txtCuitJ;
    }

    public TextField getTxtEmailJ() {
        return txtEmailJ;
    }

    public TextField getTxtTelefonoJ() {
        return txtTelefonoJ;
    }

    public TextField getTxtDireccionJ() {
        return txtDireccionJ;
    }

    public TextField getTxtRazonSocialJ() {
        return txtRazonSocialJ;
    }
}
