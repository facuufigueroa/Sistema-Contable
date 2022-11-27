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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientesController extends ViewFuntionality implements Initializable {
    private HomeVentasController homeVentasController;

    @FXML private ComboBox<String> comboBoxCliente = new ComboBox<>();
    @FXML private AnchorPane panelRegistro = new AnchorPane();

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
    @FXML private TextField txtCuitJ;
    @FXML private TextField txtEmailJ;
    @FXML private TextField txtTelefonoJ;
    @FXML private TextField txtDireccionJ;
    @FXML private TextField txtRazonSocialJ;

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
    private void obtenerPersonaFisica(){
        try {
            AnchorPane node = (AnchorPane) getPanelRegistro().getChildren().get(0);
            setCamposPersonaFisica(node);
            if (!comprobarPersonaFisicaNoNula()) {
                setPersona(getPersonaFisica());
                insertarPersona(getPersona());
            }else{ AlertaVenta.datosPersonaIncompleta(); }
        }catch (NullPointerException exception){ AlertaVenta.datosPersonaIncompleta(); }
    }
    private void setCamposPersonaFisica(AnchorPane node){
        setTxtCuit((TextField) node.getChildren().get(1));
        setTxtDni((TextField) node.getChildren().get(3));
        setTxtNombre((TextField) node.getChildren().get(5));
        setTxtApellido((TextField) node.getChildren().get(7));
        setTxtEmail((TextField) node.getChildren().get(9));
        setTxtDireccion((TextField) node.getChildren().get(11));
        setTxtTelefono((TextField) node.getChildren().get(13));
    }
    private void obtenerPersonaJuridica(){
        try {
            Node node = getPanelRegistro().getChildren().get(0);
            setCamposPersonaJuridica((AnchorPane) node);
            if (!comprobarPersonaJuridicaNoNula()){
                setPersona(getPersonaJuridica());
                insertarPersona(getPersona());
            }else{ AlertaVenta.datosPersonaIncompleta(); }
        }catch (NullPointerException exception){ AlertaVenta.datosPersonaIncompleta(); }
    }
    private void setCamposPersonaJuridica(AnchorPane node){
        setTxtCuitJ((TextField) node.getChildren().get(1));
        setTxtEmailJ((TextField) node.getChildren().get(3));
        setTxtTelefonoJ((TextField) node.getChildren().get(5));
        setTxtDireccionJ((TextField) node.getChildren().get(7));
        setTxtRazonSocialJ((TextField) node.getChildren().get(9));
    }
    public void accionElegirPersona(){ //Si selecciona una persona se carga el panel con los datos de dicha persona
           String tipoPersona = getComboBoxCliente().getValue();
           if (tipoPersona.equals("Persona Fisica")) {
               obtenerPersonaFisica();
           }else if (tipoPersona.equals("Persona Juridica")) {
               obtenerPersonaJuridica();
           }
    }

    private boolean comprobarPersonaJuridicaNoNula() {
        //cuit, razonSocial, email, direccion, telefono
        boolean cuit = getTxtCuitJ().getText().isEmpty();
        boolean razonSocial = getTxtRazonSocialJ().getText().isEmpty();
        boolean email = getTxtEmailJ().getText().isEmpty();
        boolean direccion = getTxtDireccionJ().getText().isEmpty();
        boolean telefono = getTxtTelefonoJ().getText().isEmpty();
        return cuit || razonSocial || email || direccion || telefono;
    }

    private boolean comprobarPersonaFisicaNoNula() {
        //cuit, dni, nombre, apellido, email, direccion, telefono
        boolean cuit = getTxtCuit().getText().isEmpty();
        boolean dni = getTxtDni().getText().isEmpty();
        boolean nombre = getTxtNombre().getText().isEmpty();
        boolean apellido = getTxtApellido().getText().isEmpty();
        boolean email = getTxtEmail().getText().isEmpty();
        boolean direccion = getTxtDireccion().getText().isEmpty();
        boolean telefono = getTxtTelefono().getText().isEmpty();
        return cuit || dni || nombre || apellido || email || direccion || telefono;
    }

    private Cliente getPersonaFisica(){ //dni, cuit, nombre, apellido, email, direccion, telefono
        String dni = "+" + getTxtDni().getText();
        return new Cliente(Long.parseLong(dni)
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
        boolean isComboBoxVacio = getComboBoxCliente().getValue() == null;
        if (!isComboBoxVacio){
            accionElegirPersona();
        }else{ AlertaVenta.seleccioneTipoPersona(); }
    }

    private void insertarPersona(Cliente cliente){
        try {
                getServicio().insertarPersona(cliente);
                AlertaVenta.clienteRegistrado();
                actualizarListadoPersonas();

        }catch (Exception e){ AlertaVenta.clienteNoRegistrado(); }
    }
    public void actualizarListadoPersonas(){
        ObservableList<TablaPersona> tablaVistaAsientos = FXCollections.observableArrayList(getServicio().listadoPersona());
        getTablaPersonas().setItems(tablaVistaAsientos);
    }
    /**Metodos modificar cliente**/
    @FXML
    public void accionModificarCliente(){
        if (getPersona() == null) {
            AlertaVenta.seleccioneCliente();
        }else if (getPersona().getDni() == null){
            //obtenerPersonaJuridica();
            setPersona(getPersonaJuridica());
            getServicio().modificarCliente(getPersona());
            limpiarCampoPersonaJuridica();
            actualizarListadoPersonas();
        }else{
            //obtenerPersonaFisica();
            setPersona(getPersonaFisica());
            getServicio().modificarCliente(getPersona());
            limpiarCampoPersonaFisica();
            actualizarListadoPersonas();
        }
        setPersona(null);
    }

    /**Metodos editar cliente**/
    private void limpiarCampoPersonaFisica(){
        getTxtDni().setText(null);
        getTxtDni().setEditable(true);
        getTxtCuit().setEditable(true);
        getTxtCuit().setText(null);
        getTxtNombre().setText(null);
        getTxtApellido().setText(null);
        getTxtEmail().setText(null);
        getTxtDireccion().setText(null);
        getTxtTelefono().setText(null);
    }
    private void setearCampoPersonaFisica(Cliente cliente){
        //dni, cuit, nombre, apellido, email, direccion, telefono
        getTxtDni().setText(String.valueOf(cliente.getDni()));
        getTxtDni().setEditable(false);
        getTxtCuit().setEditable(false);
        getTxtCuit().setText(cliente.getCuit());
        getTxtNombre().setText(cliente.getNombre());
        getTxtApellido().setText(cliente.getApellido());
        getTxtEmail().setText(cliente.getEmail());
        getTxtDireccion().setText(cliente.getDireccion());
        getTxtTelefono().setText(cliente.getTelefono());
    }
    private void limpiarCampoPersonaJuridica(){
        getTxtCuitJ().setText(null);
        getTxtCuitJ().setEditable(true);
        getTxtRazonSocialJ().setText(null);
        getTxtEmailJ().setText(null);
        getTxtDireccionJ().setText(null);
        getTxtTelefonoJ().setText(null);
    }
    private void setearCampoPersonaJuridica(Cliente cliente){
        //cuit, razonSocial, email, direccion, telefono
        getTxtCuitJ().setText(cliente.getCuit());
        getTxtCuitJ().setEditable(false);
        getTxtRazonSocialJ().setText(cliente.getRazonSocial());
        getTxtEmailJ().setText(cliente.getEmail());
        getTxtDireccionJ().setText(cliente.getDireccion());
        getTxtTelefonoJ().setText(cliente.getTelefono());
    }
    private TablaPersona getClienteSeleccionado(){
        try {
            return  (TablaPersona) getTablaPersonas().getSelectionModel().getSelectedItem();
        }catch (NullPointerException e){ System.out.println(e.getMessage()); AlertaVenta.seleccioneCliente(); }
        return null;
    }
    private Cliente getClienteSegunTipo(TablaPersona cliente){
        Cliente cliente1;
        if(cliente.getDni().equals(0L) || cliente.getDni() == null){ //Persona juridica
            cliente1 = new Cliente(       //cuit, razonSocial, email, direccion, telefono
                                      cliente.getCuit()
                                    , cliente.getRazonSocial()
                                    , cliente.getEmail()
                                    , cliente.getDireccion()
                                    , cliente.getTelefono()
            );
            getComboBoxCliente().setValue("Persona Juridica");
            accionElegirPersona(); //Cambio al panel de persona juridica
            setearCampoPersonaJuridica(cliente1);
            return cliente1;
        }
        cliente1 = new Cliente(       //dni, cuit, nombre, apellido, email, direccion, telefono
                                  cliente.getDni()
                                , cliente.getCuit()
                                , cliente.getNombre()
                                , cliente.getApellido()
                                , cliente.getEmail()
                                , cliente.getDireccion()
                                , cliente.getTelefono()
        );
        getComboBoxCliente().setValue("Persona Fisica");
        accionElegirPersona(); //Cambio al panel de persona fisica
        setearCampoPersonaFisica(cliente1);
        return cliente1;
    }

    @FXML
    public void accionEditarCliente() throws NullPointerException{
        TablaPersona tablaPersona = getClienteSeleccionado();
        if (tablaPersona != null){
            setPersona(getClienteSegunTipo(tablaPersona));
        }
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
    public void setTxtDni(TextField txtDni) {
        this.txtDni = txtDni;
    }

    public void setTxtCuit(TextField txtCuit) {
        this.txtCuit = txtCuit;
    }

    public void setTxtNombre(TextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public void setTxtApellido(TextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public void setTxtDireccion(TextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public void setTxtTelefono(TextField txtTelefono) {
        this.txtTelefono = txtTelefono;
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

    public void setTxtCuitJ(TextField txtCuitJ) {
        this.txtCuitJ = txtCuitJ;
    }

    public void setTxtEmailJ(TextField txtEmailJ) {
        this.txtEmailJ = txtEmailJ;
    }

    public void setTxtTelefonoJ(TextField txtTelefonoJ) {
        this.txtTelefonoJ = txtTelefonoJ;
    }

    public void setTxtDireccionJ(TextField txtDireccionJ) {
        this.txtDireccionJ = txtDireccionJ;
    }

    public void setTxtRazonSocialJ(TextField txtRazonSocialJ) {
        this.txtRazonSocialJ = txtRazonSocialJ;
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
