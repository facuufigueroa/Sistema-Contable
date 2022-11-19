package Controller;

import Model.TablaVistaAsiento;
import Model.Ventas.AlertaVenta;
import Model.Ventas.Persona;
import Model.Ventas.TablaPersona;
import Model.ViewFuntionality;
import Services.Ventas.ServiceCliente;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML private ComboBox<String> comboBoxCliente = new ComboBox<>();
    @FXML private AnchorPane panelRegistro = new AnchorPane();

    //Tabla
    @FXML private TableView<TablaPersona> tablaPersonas;
    @FXML private TableColumn<TablaPersona, Long> colDni;
    @FXML private TableColumn<TablaPersona, String> colNombre;
    @FXML private TableColumn<TablaPersona, String> colApellido;
    @FXML private TableColumn<TablaPersona, String> colCuit;
    @FXML private TableColumn<TablaPersona, String> colDireccion;
    @FXML private TableColumn<TablaPersona, String> colTelefono;
    @FXML private TableColumn<TablaPersona, String> colEmail;
    @FXML private TableColumn<TablaPersona, String> colRazonSocial;
    @FXML private TableColumn<TablaPersona, String> colTipoPersona;
    @FXML private TableColumn<TablaPersona, String> colEstado;

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

    private Persona persona;

    //Servicio
    private ServiceCliente servicio = new ServiceCliente();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarComboBox();
        iniciarTabla();
    }

    private void iniciarComboBox() {
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll("Persona Fisica", "Persona Juridica");
        getComboBoxCliente().setItems(cuentas);
    }
    //Long dni, String nombre, String apellido, String cuit, String direccion, String telefono
    //            , String email, String razonSocial, String  tipoPersona, String estado
    private void iniciarTabla(){
        ObservableList<TablaPersona> tablaVistaAsientos = FXCollections.observableArrayList(getServicio().listadoPersona());
        getColDni().setCellValueFactory(new PropertyValueFactory<>("dni"));
        getColNombre().setCellValueFactory(new PropertyValueFactory<>("nombre"));
        getColApellido().setCellValueFactory(new PropertyValueFactory<>("apellido"));
        getColCuit().setCellValueFactory(new PropertyValueFactory<>("cuit"));
        getColDireccion().setCellValueFactory(new PropertyValueFactory<>("direccion"));
        getColTelefono().setCellValueFactory(new PropertyValueFactory<>("telefono"));
        getColEmail().setCellValueFactory(new PropertyValueFactory<>("email"));
        getColRazonSocial().setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        getColTipoPersona().setCellValueFactory(new PropertyValueFactory<>("tipoPersona"));
        getColEstado().setCellValueFactory(new PropertyValueFactory<>("estado"));

        getTablaPersonas().setItems(tablaVistaAsientos);
    }

    /**
     *   Paneles de registros de personas
     */
    public AnchorPane personaFisica()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ventas-View/clientes/persona-fisica.fxml"));
        Parent parent = loader.load();
        return  (AnchorPane) parent;
    }
    public AnchorPane personaJuridica() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ventas-View/clientes/persona-juridica.fxml"));
        Parent parent = loader.load();
        return  (AnchorPane) parent;
    }


    @FXML
    public void accionCambiarPersona() throws IOException {
        String tipoPersona = getComboBoxCliente().getValue();
        if (tipoPersona.equals("Persona Fisica")){
            getPanelRegistro().getChildren().clear();
            getPanelRegistro().getChildren().setAll(personaFisica());
        }else if (tipoPersona.equals("Persona Juridica")){
            getPanelRegistro().getChildren().clear();
            getPanelRegistro().getChildren().setAll(personaJuridica());
        }
    }
    private void accionElegirPersona(){ //Si selecciona una persona se carga el panel con los datos de dicha persona
       try {
           String tipoPersona = getComboBoxCliente().getValue();
           if (tipoPersona.isEmpty()){
               AlertaVenta.seleccioneTipoPersona();
           }
           if (tipoPersona.equals("Persona Fisica")) {
               if (!comprobarPersonaFisicaNoNula()) {
                   //setear panel persona fisica
                   setPersona(getPersonaFisica());
               } else {
                   //Alerta datos persona fisica incompletos
                   AlertaVenta.datosPersonaIncompleta();
               }
           }
           if (tipoPersona.equals("Persona Juridica")) {
               //seter panel persona juridica
               if (!comprobarPersonaJuridicaNoNula()) {
                   setPersona(getPersonaJuridica());
               } else {
                   //Alerta datos persona juridica incompletos
                   AlertaVenta.datosPersonaIncompleta();
               }
           }
           System.out.println(getPersona().toString());
       }catch (NullPointerException e){ System.out.println("Campos vacios"); }
    }

    private boolean comprobarPersonaJuridicaNoNula() {
        //cuit, razonSocial, email, direccion, telefono
        boolean cuit = !getTxtCuitJ().getText().isEmpty();
        boolean razonSocial = !getTxtRazonSocialJ().getText().isEmpty();
        boolean email = !getTxtEmailJ().getText().isEmpty();
        boolean direccion = !getTxtDireccionJ().getText().isEmpty();
        boolean telefono = !getTxtTelefonoJ().getText().isEmpty();
        return cuit && razonSocial && email && direccion && telefono;
    }

    private boolean comprobarPersonaFisicaNoNula() {
        //cuit, dni, nombre, apellido, email, direccion, telefono
        boolean cuit = !getTxtCuit().getText().isEmpty();
        boolean dni = !getTxtDni().getText().isEmpty();
        boolean nombre = !getTxtNombre().getText().isEmpty();
        boolean apellido = !getTxtApellido().getText().isEmpty();
        boolean email = !getTxtEmail().getText().isEmpty();
        boolean direccion = !getTxtDireccion().getText().isEmpty();
        boolean telefono = !getTxtTelefono().getText().isEmpty();
        return cuit && dni && nombre && apellido && email && direccion && telefono;
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
        accionElegirPersona();
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

    public ServiceCliente getServicio() {
        return servicio;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TableColumn<TablaPersona, Long> getColDni() {
        return colDni;
    }

    public TableColumn<TablaPersona, String> getColNombre() {
        return colNombre;
    }

    public TableColumn<TablaPersona, String> getColApellido() {
        return colApellido;
    }

    public TableColumn<TablaPersona, String> getColCuit() {
        return colCuit;
    }

    public TableColumn<TablaPersona, String> getColDireccion() {
        return colDireccion;
    }

    public TableColumn<TablaPersona, String> getColTelefono() {
        return colTelefono;
    }

    public TableColumn<TablaPersona, String> getColEmail() {
        return colEmail;
    }

    public TableColumn<TablaPersona, String> getColRazonSocial() {
        return colRazonSocial;
    }

    public TableColumn<TablaPersona, String> getColTipoPersona() {
        return colTipoPersona;
    }

    public TableColumn<TablaPersona, String> getColEstado() {
        return colEstado;
    }
}
