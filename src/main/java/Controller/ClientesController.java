package Controller;
import Model.Validacion;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
public class ClientesController extends ViewFuntionality implements Initializable {
    private HomeVentasController homeVentasController;
    @FXML private Button btnEditar;
    @FXML private Button btnHabilitarCliente;
    @FXML private Button btnDeshabilitar;
    @FXML private Button btnGuardar;
    @FXML private Button btnVolver;
    @FXML private Button buttonClose;
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

    //Busqueda
    @FXML private TextField txtBuscarPorDni = new TextField();
    @FXML private TextField txtBuscarPorNombre = new TextField();

    //Persona Fisica
    @FXML private TextField txtDni = new TextField();
    @FXML private TextField txtCuit = new TextField();
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtTelefono;
    @FXML private ComboBox<String> comboIva = new ComboBox<>();

    //Persona Juridica
    @FXML private TextField txtCuitJ = new TextField();
    @FXML private TextField txtNombreJ;
    @FXML private TextField txtEmailJ;
    @FXML private TextField txtTelefonoJ;
    @FXML private TextField txtDireccionJ;
    @FXML private TextField txtRazonSocialJ;
    @FXML private ComboBox<String> comboIvaJ = new ComboBox<>();

    private Cliente cliente;
    private ObservableList<TablaPersona> personasPorDni;
    private HashSet<TablaPersona> personas = new HashSet<>();

    //Servicio
    private ServiceCliente servicio = new ServiceCliente();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personasPorDni = FXCollections.observableArrayList();
        iniciarTabla();
        iniciarComboBox();
        iniciarComboBoxTipoIvaJ();
        iniciarComboBoxTipoIva();
        Validacion.limitarCantidadCaracteresYSoloNumero(this.getTxtDni(), 8);
        Validacion.limitarCantidadCaracteresYSoloNumero(this.getTxtCuit(), 11);
        Validacion.limitarCantidadCaracteresYSoloNumero(this.getTxtCuitJ(), 11);
        Validacion.limitarCamposDeTexto(this.txtBuscarPorDni, 8);
        Validacion.limitarCamposDeTexto(this.txtBuscarPorNombre, 30);
    }
    private void deshabilitarComboBoxSeleccionarPersona(boolean deshabilitar){
        getComboBoxCliente().setDisable(deshabilitar);
    }
    private void iniciarComboBox() {
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll("Persona Fisica", "Persona Juridica");
        getComboBoxCliente().setItems(cuentas);
    }
    private void iniciarComboBoxTipoIvaJ(){
        ObservableList<String> tipo = FXCollections.observableArrayList();
        tipo.addAll("responsable inscripto", "monotributista", "excento");
        getComboIvaJ().setItems(tipo);
    }
    private void iniciarComboBoxTipoIva(){
        ObservableList<String> tipo = FXCollections.observableArrayList();
        tipo.addAll("consumidor final");
        getComboIva().setItems(tipo);
        getComboIva().setValue("consumidor final");
        getComboIva().setDisable(true);
    }

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
    private void obtenerPersonaFisica(String operacion){
        try {
            AnchorPane node = (AnchorPane) getPanelRegistro().getChildren().get(0);
            setCamposPersonaFisica(node);
            if (!comprobarPersonaFisicaNoNula()) {
                setPersona(getPersonaFisica());
                if (!getServicio().existeDni(getPersona().getDni())){
                    insertarPersona(getPersona());
                }else{ if(operacion.equals("GUARDAR")){ AlertaVenta.dniExistente();} }
            }else{ if(operacion.equals("GUARDAR")){ AlertaVenta.datosPersonaIncompleta();} }
        }catch (NullPointerException exception){ if(operacion.equals("GUARDAR")){ AlertaVenta.datosPersonaIncompleta(); }; }
    }
    private void setCamposPersonaFisica(AnchorPane node){
        setTxtCuit((TextField) node.getChildren().get(1));
        setTxtDni((TextField) node.getChildren().get(3));
        setTxtNombre((TextField) node.getChildren().get(5));
        setTxtApellido((TextField) node.getChildren().get(7));
        setTxtEmail((TextField) node.getChildren().get(9));
        setTxtDireccion((TextField) node.getChildren().get(11));
        setTxtTelefono((TextField) node.getChildren().get(13));
        setComboIva((ComboBox) node.getChildren().get(15));
    }
    private void obtenerPersonaJuridica(String operacion){
        try {
            Node node = getPanelRegistro().getChildren().get(0);
            setCamposPersonaJuridica((AnchorPane) node);
            if (!comprobarPersonaJuridicaNoNula()){
                setPersona(getPersonaJuridica());
                insertarPersona(getPersona());
            }else{ AlertaVenta.datosPersonaIncompleta(); }
        }catch (NullPointerException exception){ if (operacion.equals("GUARDAR")){ AlertaVenta.datosPersonaIncompleta(); } }
    }
    private void setCamposPersonaJuridica(AnchorPane node){
        setTxtCuitJ((TextField) node.getChildren().get(1));
        setTxtNombreJ((TextField) node.getChildren().get(3));
        setTxtEmailJ((TextField) node.getChildren().get(5));
        setTxtTelefonoJ((TextField) node.getChildren().get(7));
        setTxtDireccionJ((TextField) node.getChildren().get(9));
        setTxtRazonSocialJ((TextField) node.getChildren().get(11));
        setComboIvaJ((ComboBox) node.getChildren().get(13));
    }
    public void accionElegirPersona(String operacion){ //Si selecciona una persona se carga el panel con los datos de dicha persona
           String tipoPersona = getComboBoxCliente().getValue();
           if (tipoPersona.equals("Persona Fisica")) {
               obtenerPersonaFisica(operacion);
           }else if (tipoPersona.equals("Persona Juridica")) {
               obtenerPersonaJuridica(operacion);
           }
    }

    private boolean comprobarPersonaJuridicaNoNula() {
        //cuit, razonSocial, email, direccion, telefono, tipoIVa
        boolean cuit = getTxtCuitJ().getText().isEmpty();
        boolean nombre = getTxtNombreJ().getText().isEmpty();
        boolean razonSocial = getTxtRazonSocialJ().getText().isEmpty();
        boolean email = getTxtEmailJ().getText().isEmpty();
        boolean direccion = getTxtDireccionJ().getText().isEmpty();
        boolean telefono = getTxtTelefonoJ().getText().isEmpty();
        boolean tipoIva = getComboIvaJ().getValue().isEmpty();
        return cuit || nombre || razonSocial || email || direccion || telefono || tipoIva;
    }

    private boolean comprobarPersonaFisicaNoNula() {
        //cuit, dni, nombre, apellido, email, direccion, telefono, tipoIVa
        boolean cuit = getTxtCuit().getText().isEmpty();
        boolean dni = getTxtDni().getText().isEmpty();
        boolean nombre = getTxtNombre().getText().isEmpty();
        boolean apellido = getTxtApellido().getText().isEmpty();
        boolean email = getTxtEmail().getText().isEmpty();
        boolean direccion = getTxtDireccion().getText().isEmpty();
        boolean telefono = getTxtTelefono().getText().isEmpty();
        boolean tipoIva = getComboIva().getValue().isEmpty();
        return cuit || dni || nombre || apellido || email || direccion || telefono || tipoIva;
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
                , getComboIva().getValue()
        );
    }
    private Cliente getPersonaJuridica(){ //cuit, nombre, razonSocial, email, direccion, telefono
        return new Cliente(   getTxtCuitJ().getText()
                            , getTxtNombreJ().getText()
                            , getTxtRazonSocialJ().getText()
                            , getTxtEmailJ().getText()
                            , getTxtDireccionJ().getText()
                            , getTxtTelefonoJ().getText()
                            , getComboIvaJ().getValue()
        );
    }

    @FXML
    public void accionGuardarPersona(ActionEvent event) {
        boolean isComboBoxVacio = getComboBoxCliente().getValue() == null;
        if (!isComboBoxVacio){
            accionElegirPersona("GUARDAR");
        }else{ AlertaVenta.seleccioneTipoPersona(); }
    }

    private void insertarPersona(Cliente cliente){
        try {
            if (!getServicio().existeCuit(cliente.getCuit())){
                getServicio().insertarPersona(cliente);
                AlertaVenta.clienteRegistrado();
                actualizarListadoPersonas();
            }else{ AlertaVenta.cuitExistente(); }
        }catch (Exception e){ AlertaVenta.clienteNoRegistrado(); }
    }
    public void actualizarListadoPersonas(){
        ObservableList<TablaPersona> tablaVistaAsientos = FXCollections.observableArrayList(getServicio().listadoPersona());
        getTablaPersonas().setItems(tablaVistaAsientos);
    }
    private void deshabilitarBotones(){
        getBtnDeshabilitar().setDisable(true);
        getBtnGuardar().setDisable(true);
        getBtnHabilitarCliente().setDisable(true);
        getBtnEditar().setDisable(true);
        deshabilitarComboBoxSeleccionarPersona(true);
    }
    private void habilitarBotones(){
        getBtnDeshabilitar().setDisable(false);
        getBtnGuardar().setDisable(false);
        getBtnHabilitarCliente().setDisable(false);
        getBtnEditar().setDisable(false);
        deshabilitarComboBoxSeleccionarPersona(false);
    }

    /**Habilitar y deshabilitar cliente**/
    private Cliente getClientePorEstado(TablaPersona cliente){
        if(cliente.getDni().equals(0L) || cliente.getDni() == null){ //Persona juridica
            return new Cliente(       //cuit, nombre, razonSocial, email, direccion, telefono, condicionIva
                      cliente.getCuit()
                    , cliente.getRazonSocial()
                    , cliente.getEmail()
                    , cliente.getDireccion()
                    , cliente.getTelefono()
            );
        }
        return new Cliente(       //dni, cuit, nombre, apellido, email, direccion, telefono
                cliente.getDni()
                , cliente.getCuit()
                , cliente.getNombre()
                , cliente.getApellido()
                , cliente.getEmail()
                , cliente.getDireccion()
                , cliente.getTelefono()
        );
    }

    @FXML
    public void accionHabilitarCliente(){
        try {
            TablaPersona seleccionado =  (TablaPersona) getTablaPersonas().getSelectionModel().getSelectedItem();
            Cliente cliente = getClientePorEstado(seleccionado);
            if (seleccionado.getEstado().equals("Habilitado")){
                AlertaVenta.clienteHabilitado();
            }else {
                cliente.setEstado(true);
                getServicio().modificarEstado(cliente);
                AlertaVenta.clienteHabilitadoCorrectamente();
                actualizarListadoPersonas();
            }
        }catch (NullPointerException exception){ AlertaVenta.seleccioneCliente(); }
    }
    @FXML
    public void accionDeshabilitarCliente(){
        try {
            TablaPersona seleccionado =  (TablaPersona) getTablaPersonas().getSelectionModel().getSelectedItem();
            Cliente cliente = getClientePorEstado(seleccionado);
            if (seleccionado.getEstado().equals("Deshabilitado")){
                AlertaVenta.clienteDeshabilitado();
            }else {
                cliente.setEstado(false);
                getServicio().modificarEstado(cliente);
                AlertaVenta.clienteDeshabilitadoCorrectamente();
                actualizarListadoPersonas();
            }
        }catch (NullPointerException exception){ AlertaVenta.seleccioneCliente(); }
    }

    /**Metodos buscar cliente por dni**/
    @FXML
    public void accionBuscarClientePorDni(KeyEvent event){
        ObservableList<TablaPersona> listadoPersonas = getTablaPersonas().getItems();
        try{
            String filtroCodigo = getTxtBuscarPorDni().getText();
            if (filtroCodigo.isEmpty()){
                actualizarListadoPersonas();
                personas.clear();
            }else{
                personas.clear();
                listadoPersonas.forEach(persona ->
                        {
                            String dni = String.valueOf(persona.getDni());
                            if (dni.contains(filtroCodigo)){
                                personas.add(persona);
                                personasPorDni.setAll(personas);
                            }
                        }
                );
                getTablaPersonas().setItems(personasPorDni);
            }
        }catch (Exception exception){ System.out.println(exception.getMessage()); }
    }

    /**Metodo buscar cliente por nombre**/
    @FXML
    public void accionBuscarClientePorNombre(KeyEvent event){
        ObservableList<TablaPersona> listadoPersonas = getTablaPersonas().getItems();
        try{
            String filtroCodigo = getTxtBuscarPorNombre().getText();
            if (filtroCodigo.isEmpty()){
                actualizarListadoPersonas();
                personas.clear();
            }else{
                personas.clear();
                listadoPersonas.forEach(persona ->
                        {
                            String nombre = String.valueOf(persona.getNombre()).toLowerCase();
                            if (nombre.contains(filtroCodigo.toLowerCase())){
                                personas.add(persona);
                                personasPorDni.setAll(personas);
                            }
                        }
                );
                getTablaPersonas().setItems(personasPorDni);
            }
        }catch (Exception exception){ System.out.println(exception.getMessage()); }
    }

    /**Metodo modificar cliente**/
    @FXML
    public void accionModificarCliente(){
        if (getPersona() == null) { AlertaVenta.seleccioneCliente(); }
        else if (getPersona().getDni() == null){
            setPersona(getPersonaJuridica());
            getServicio().modificarCliente(getPersona());
            limpiarCampoPersonaJuridica();
            actualizarListadoPersonas();
            habilitarBotones();
        }else{
            setPersona(getPersonaFisica());
            getServicio().modificarCliente(getPersona());
            limpiarCampoPersonaFisica();
            actualizarListadoPersonas();
            habilitarBotones();
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
        getTxtNombreJ().setText(null);
        getTxtRazonSocialJ().setText(null);
        getTxtEmailJ().setText(null);
        getTxtDireccionJ().setText(null);
        getTxtTelefonoJ().setText(null);
    }
    private void setearCampoPersonaJuridica(Cliente cliente){
        //cuit, razonSocial, email, direccion, telefono
        getTxtCuitJ().setText(cliente.getCuit());
        getTxtCuitJ().setEditable(false);
        getTxtNombreJ().setText(cliente.getNombre());
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
            cliente1 = new Cliente(
                                      cliente.getCuit()
                                    , cliente.getNombre()
                                    , cliente.getRazonSocial()
                                    , cliente.getEmail()
                                    , cliente.getDireccion()
                                    , cliente.getTelefono()
                                    , ""
            );
            getComboBoxCliente().setValue("Persona Juridica");
            accionElegirPersona("MODIFICAR"); //Cambio al panel de persona juridica
            setearCampoPersonaJuridica(cliente1);
            return cliente1;
        }
        cliente1 = new Cliente(
                                  cliente.getDni()
                                , cliente.getCuit()
                                , cliente.getNombre()
                                , cliente.getApellido()
                                , cliente.getEmail()
                                , cliente.getDireccion()
                                , cliente.getTelefono()
        );
        getComboBoxCliente().setValue("Persona Fisica");
        accionElegirPersona("MODIFICAR"); //Cambio al panel de persona fisica
        setearCampoPersonaFisica(cliente1);
        return cliente1;
    }

    @FXML
    public void accionEditarCliente() throws NullPointerException{
        TablaPersona tablaPersona = getClienteSeleccionado();
        if (tablaPersona != null){
            setPersona(getClienteSegunTipo(tablaPersona));
            deshabilitarBotones();
        }else{ AlertaVenta.seleccioneCliente(); }
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
    public HomeVentasController getHomeVentasController() { return homeVentasController; }
    public void setHomeVentasController(HomeVentasController homeVentasController) { this.homeVentasController = homeVentasController; }
    public ComboBox<String> getComboBoxCliente() { return comboBoxCliente; }
    public AnchorPane getPanelRegistro() { return panelRegistro; }
    public ServiceCliente getServicio() { return servicio; }
    public TextField getTxtBuscarPorDni() { return txtBuscarPorDni; }
    public void setTxtBuscarPorDni(TextField txtBuscarPorDni) { this.txtBuscarPorDni = txtBuscarPorDni; }
    public TextField getTxtBuscarPorNombre() { return txtBuscarPorNombre; }
    public void setTxtBuscarPorNombre(TextField txtBuscarPorNombre) { this.txtBuscarPorNombre = txtBuscarPorNombre; }
    public TableView getTablaPersonas() { return tablaPersonas; }
    public void setPanelRegistro(AnchorPane panelRegistro) { this.panelRegistro = panelRegistro; }
    public TextField getTxtDni() { return txtDni; }
    public TextField getTxtCuit() { return txtCuit; }
    public TextField getTxtNombre() { return txtNombre; }
    public TextField getTxtApellido() { return txtApellido; }
    public TextField getTxtEmail() { return txtEmail; }
    public TextField getTxtDireccion() { return txtDireccion; }
    public TextField getTxtTelefono() { return txtTelefono; }
    public ComboBox<String> getComboIva() { return comboIva; }
    public void setTxtDni(TextField txtDni) { this.txtDni = txtDni; }
    public void setTxtCuit(TextField txtCuit) { this.txtCuit = txtCuit; }
    public void setTxtNombre(TextField txtNombre) { this.txtNombre = txtNombre; }
    public void setTxtApellido(TextField txtApellido) { this.txtApellido = txtApellido; }
    public void setTxtEmail(TextField txtEmail) { this.txtEmail = txtEmail; }
    public void setTxtDireccion(TextField txtDireccion) { this.txtDireccion = txtDireccion; }
    public void setTxtTelefono(TextField txtTelefono) { this.txtTelefono = txtTelefono; }
    public void setComboIva(ComboBox<String> comboIva) { this.comboIva = comboIva; }
    public TextField getTxtCuitJ() { return txtCuitJ; }
    public TextField getTxtNombreJ() { return txtNombreJ; }
    public TextField getTxtEmailJ() { return txtEmailJ; }
    public TextField getTxtTelefonoJ() { return txtTelefonoJ; }
    public TextField getTxtDireccionJ() { return txtDireccionJ; }
    public TextField getTxtRazonSocialJ() { return txtRazonSocialJ; }
    public ComboBox<String> getComboIvaJ() { return comboIvaJ; }
    public void setTxtCuitJ(TextField txtCuitJ) { this.txtCuitJ = txtCuitJ; }
    public void setTxtNombreJ(TextField txtNombreJ) { this.txtNombreJ = txtNombreJ; }
    public void setTxtEmailJ(TextField txtEmailJ) { this.txtEmailJ = txtEmailJ; }
    public void setTxtTelefonoJ(TextField txtTelefonoJ) { this.txtTelefonoJ = txtTelefonoJ; }
    public void setTxtDireccionJ(TextField txtDireccionJ) { this.txtDireccionJ = txtDireccionJ; }
    public void setTxtRazonSocialJ(TextField txtRazonSocialJ) { this.txtRazonSocialJ = txtRazonSocialJ; }
    public void setComboIvaJ(ComboBox<String> comboIvaJ) { this.comboIvaJ = comboIvaJ; }
    public Cliente getPersona() { return cliente; }
    public void setPersona(Cliente cliente) { this.cliente = cliente; }
    public TableColumn<TablaPersona, Long> getColDni() { return colDni; }
    public TableColumn<TablaPersona, String> getColNombre() { return colNombre; }
    public TableColumn<TablaPersona, String> getColApellido() { return colApellido; }
    public TableColumn<TablaPersona, String> getColCuit() { return colCuit; }
    public TableColumn<TablaPersona, String> getColDireccion() { return colDireccion; }
    public TableColumn<TablaPersona, String> getColTelefono() { return colTelefono; }
    public TableColumn<TablaPersona, String> getColEmail() { return colEmail; }
    public TableColumn<TablaPersona, String> getColRazonSocial() { return colRazonSocial; }
    public TableColumn<TablaPersona, String> getColTipoPersona() { return colTipoPersona; }
    public TableColumn<TablaPersona, String> getColEstado() { return colEstado; }
    public Button getBtnEditar() { return btnEditar; }
    public Button getBtnHabilitarCliente() { return btnHabilitarCliente; }
    public Button getBtnDeshabilitar() { return btnDeshabilitar; }
    public Button getBtnGuardar() { return btnGuardar; }

    public Button getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(Button btnVolver) {
        this.btnVolver = btnVolver;
    }

    public Button getButtonClose() {
        return buttonClose;
    }

    public void setButtonClose(Button buttonClose) {
        this.buttonClose = buttonClose;
    }
}
