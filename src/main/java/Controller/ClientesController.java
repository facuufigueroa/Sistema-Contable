package Controller;

import Model.Ventas.AlertaVenta;
import Model.Ventas.Cliente;
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
import java.util.ResourceBundle;

public class ClientesController extends ViewFuntionality implements Initializable {
    private HomeVentasController homeVentasController;

    @FXML private ComboBox<String> comboBoxCliente = new ComboBox<>();
    @FXML private AnchorPane panelRegistro;

    //Tabla
    @FXML private TableView<TablaPersona> tablaPersonas = new TableView<>();
    @FXML private TableColumn<TablaPersona, Long> colDni = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colNombre = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colApellido = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colCuit = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colDireccion = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colTelefono = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colEmail = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colRazonSocial = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colTipoPersona = new TableColumn<>();
    @FXML private TableColumn<TablaPersona, String> colEstado = new TableColumn<>();

    //Persona Fisica
    @FXML private TextField txtDni;
    @FXML private TextField txtCuit;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtTelefono;

    //Persona Juridica
    @FXML private TextField txtCuitJ = new TextField();
    @FXML private TextField txtEmailJ = new TextField();
    @FXML private TextField txtTelefonoJ = new TextField();
    @FXML private TextField txtDireccionJ = new TextField();
    @FXML private TextField txtRazonSocialJ = new TextField();

    private Cliente cliente;

    //Servicio
    private ServiceCliente servicio = new ServiceCliente();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTabla();
        iniciarComboBox();

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
    public void personaFisica()throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ventas-View/clientes/persona-fisica.fxml"));
        Parent parent = loader.load();
        getPanelRegistro().getChildren().clear();
        getPanelRegistro().getChildren().addAll(parent);
    }
    public void personaJuridica() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Ventas-View/clientes/persona-juridica.fxml"));
        Parent parent = loader.load();
        getPanelRegistro().getChildren().clear();
        getPanelRegistro().getChildren().addAll(parent);
    }


    @FXML
    public void accionCambiarPersona() throws IOException {
        String tipoPersona = getComboBoxCliente().getValue();
        if (tipoPersona.equals("Persona Fisica")){
            personaFisica();
        }else if (tipoPersona.equals("Persona Juridica")){
            personaJuridica();
        }
    }
    private void accionElegirPersona() throws NullPointerException{ //Si selecciona una persona se carga el panel con los datos de dicha persona
       try {
           String tipoPersona = getComboBoxCliente().getValue();
           if (tipoPersona.equals("Persona Fisica")) {
               if (!comprobarPersonaFisicaNoNula()) {
                   //setear panel persona fisica
                   setPersona(getPersonaFisica());
                   getServicio().insertarPersona(getPersona());
               } else {
                   //Alerta datos persona fisica incompletos
                   AlertaVenta.datosPersonaIncompleta();
               }
           }
           if (tipoPersona.equals("Persona Juridica")) {
               //seter panel persona juridica
               if (!comprobarPersonaJuridicaNoNula()) {
                   setPersona(getPersonaJuridica());
                   getServicio().insertarPersona(getPersona());
               } else {
                   //Alerta datos persona juridica incompletos
                   AlertaVenta.datosPersonaIncompleta();
               }
           }
       }catch (NullPointerException e){ System.out.println("Campos nulos"); }
    }

    private boolean comprobarPersonaJuridicaNoNula() {
        //cuit, razonSocial, email, direccion, telefono
        boolean cuit = getTxtCuitJ().getText() == null;
        boolean razonSocial = getTxtRazonSocialJ().getText() == null;
        boolean email = getTxtEmailJ().getText() == null;
        boolean direccion = getTxtDireccionJ().getText() == null;
        boolean telefono = getTxtTelefonoJ().getText() == null;
        return cuit || razonSocial || email || direccion || telefono;
    }

    private boolean comprobarPersonaFisicaNoNula() {
        //cuit, dni, nombre, apellido, email, direccion, telefono
        boolean cuit = getTxtCuit().getText() == null;
        boolean dni = getTxtDni().getText() == null;
        boolean nombre = getTxtNombre().getText() == null;
        boolean apellido = getTxtApellido().getText() == null;
        boolean email = getTxtEmail().getText() == null;
        boolean direccion = getTxtDireccion().getText() == null;
        boolean telefono = getTxtTelefono().getText() == null;
        return cuit || dni || nombre || apellido || email || direccion || telefono;
    }

    private Cliente getPersonaFisica(){
        long dni = Long.parseLong((getTxtDni().getText()));
        return new Cliente(  dni
                            , getTxtCuit().getText()
                            , getTxtNombre().getText()
                            , getTxtApellido().getText()
                            , getTxtEmail().getText()
                            , getTxtDireccion().getText()
                            , getTxtTelefono().getText()
        );
    }
    private Cliente getPersonaJuridica(){ //cuit, razonSocial, email, direccion, telefono
        return new Cliente(   getTxtCuitJ().getText()
                            , getTxtRazonSocialJ().getText()
                            , getTxtEmailJ().getText()
                            , getTxtDireccionJ().getText()
                            , getTxtTelefonoJ().getText()
        );
    }

    @FXML
    private void accionGuardarPersona() {
        //Si dni == null => obtenerPersonaJuridica
        //Si Razon social == null obtenerPersonaFisica
        //Sino error
        boolean isComboBoxVacio = getComboBoxCliente().getValue() == null;
        if (!isComboBoxVacio){
            accionElegirPersona();
        }else{ AlertaVenta.seleccioneTipoPersona(); }
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

    public Cliente getPersona() {
        return cliente;
    }

    public void setPersona(Cliente cliente) {
        this.cliente = cliente;
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
