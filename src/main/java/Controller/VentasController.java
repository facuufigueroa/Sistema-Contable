package Controller;

import Model.*;
import Model.Ventas.*;
import Reportes.ReporteFactura;
import Services.ServiceAsiento;
import Services.ServiceCalcularSaldoCuenta;
import Services.ServiceProducto;
import Services.Ventas.ServiceCliente;
import Services.Ventas.ServiceFactura;
import Services.Ventas.ServiceRemito;
import Services.Ventas.ServiceVenta;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentasController extends ViewFuntionality implements Initializable {

    private HomeVentasController homeVentasController;

    private SeleccionarPagoController seleccionarPagoController;

    private SeleccionClienteController seleccionClienteController;

    @FXML
    private DatePicker fechaVentaFactura;

    private final LocalDate fechaActual = LocalDate.now();
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtFormaPago;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtCondicionIva;
    @FXML
    private TextField txtIVA;
    @FXML
    private TableView<TablaVistaVenta> tablaVenta;
    @FXML
    private TableColumn columProducto;
    @FXML
    private TableColumn columDescripcion;
    @FXML
    private TableColumn columCantidad;
    @FXML
    private TableColumn columPrecio;
    @FXML
    private TableColumn columTotal;
    @FXML
    private Button btnContinuar;
    private Venta venta = Venta.getInstance();
    private User user = User.getInstance();

    private ServiceCliente serviceCliente = new ServiceCliente();

    private ServiceVenta serviceVenta = new ServiceVenta();

    private ServiceProducto serviceProducto = new ServiceProducto();

    private ObservableList<TablaVistaVenta> tablaVentaObservableList;
    ArrayList<TablaVistaVenta> ventaProductos = venta.getVentaProductos();

    ArrayList<TablaVistaAsiento> asientoCuentas = new ArrayList<>();
    ArrayList<Producto> productos = venta.getProductos();

    private ServiceFactura serviceFactura = new ServiceFactura();

    private ReporteFactura reporteFactura = new ReporteFactura();

    private RemitoFacturaController remitoFacturaController;

    private ServiceAsiento serviceAsiento = new ServiceAsiento();

    private ServiceRemito serviceRemito = new ServiceRemito();

    private ArrayList<Remito> remitos = new ArrayList<Remito>();

    private ServiceCalcularSaldoCuenta serviceCalcularSaldo = new ServiceCalcularSaldoCuenta();

    /*Atributos para enviar a remito y factura*/

    private String numeroFac;
    private double iva;
    private double subtotal;
    private double total;
    private String numRemito;

    /*-----------------*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatosVenta();
    }
    public void cargarDatosVenta(){
        txtCondicionIva.setText(serviceCliente.obtenerCondicionIva(venta.getIdCliente()));
        txtCliente.setText(serviceCliente.obtenerNombreCliente(venta.getIdCliente()));
        txtFormaPago.setText(obtenerTxtFP());
        listarProductosVenta();
        txtIVA.setText(obtenerIVA().toString());
        venta.setTotalBruto(obtenerTotalVenta());
        venta.setTotalNeto((obtenerTotalVenta() + obtenerIVA()));
        txtTotal.setText(String.valueOf(venta.getTotalNeto()));
        venta.setTotales(obtenerTotales(venta.getCuotas()));
    }

    public String obtenerTxtFP(){
        String formaPago = obtenerFormaPago(venta.getFormaPago());
       if(formaPago.toUpperCase() == "CUOTAS"){
          return formaPago + " " + venta.getCuotas();
       }
       else{
           return formaPago;
       }
    }
    public String obtenerFormaPago(int formaPago){
        return serviceVenta.obtenerFormaPago(formaPago);
    }

    public void listarProductosVenta(){
        tablaVentaObservableList = FXCollections.observableArrayList(ventaProductos);
        columProducto.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta, String>("producto"));
        columDescripcion.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta, String>("descripcion"));
        columCantidad.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta,Integer>("cantidad"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta,Double>("precioUnitario"));
        columTotal.setCellValueFactory(new PropertyValueFactory<TablaVistaVenta, Double>("precioTotal"));
        tablaVenta.setItems(tablaVentaObservableList);
    }
    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/seleccionar-forma-pago.fxml"));
        Parent parent = fxmlLoader.load();
        setSeleccionarPagoController(loadSeleccionFormaPago(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage formaPagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getSeleccionarPagoController().setVentana(formaPagoStage);
        getSeleccionarPagoController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    public void accionBtnQuitarProducto(){
        try {
            TablaVistaVenta producto = tablaVenta.getSelectionModel().getSelectedItem();
            ventaProductos.remove(tablaVenta.getSelectionModel().getSelectedIndex());
            productos.remove(serviceProducto.obtenerProductoPorId(producto.getIdProducto()));
            cargarDatosVenta();
        }
        catch (NullPointerException e){
        }
    }

    private SeleccionarPagoController loadSeleccionFormaPago(SeleccionarPagoController selectPagoController){ return selectPagoController; }


    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }

    public SeleccionarPagoController getSeleccionarPagoController() {
        return seleccionarPagoController;
    }

    public void setSeleccionarPagoController(SeleccionarPagoController seleccionarPagoController) {
        this.seleccionarPagoController = seleccionarPagoController;
    }

    private SeleccionClienteController loadSeleccionCliente(SeleccionClienteController selectClienteController){ return selectClienteController; }

    public SeleccionClienteController getSeleccionClienteController() {
        return seleccionClienteController;
    }

    public void setSeleccionClienteController(SeleccionClienteController seleccionClienteController) {
        this.seleccionClienteController = seleccionClienteController;
    }

    public void accionBtnGenerarVenta(ActionEvent actionEvent) throws SQLException {
            Venta venta1 = new Venta(venta.getIdCliente(), venta.getTotalBruto(), venta.getTotalNeto(),
                    venta.getTotales(), venta.getFormaPago(), user.getId());
            serviceVenta.insertarVenta(venta1);
            insertarVentaProducto();
            Alerta.alertaVentaRegistrada();
            String numeroFactura=insertarFactura();
            numeroFac=numeroFactura;
            numRemito=insertarRemito();
    }

    public void insertarVentaProducto() throws SQLException{
        int idVenta = serviceVenta.obtenerIdVenta();
            for (TablaVistaVenta venta1 : ventaProductos) {
                TablaVistaVenta ventaProducto = new TablaVistaVenta(idVenta, venta1.getIdProducto(),
                        venta1.getCantidad(), venta1.getPrecioUnitario(),venta1.getPrecioTotal());
                serviceVenta.insertarVenta_producto(ventaProducto);
            }
    }
    public Double obtenerTotalVenta(){
        Double total = 0.0;
        for(TablaVistaVenta producto : ventaProductos){
            total += producto.getPrecioTotal();
        }
        return total;
    }
    public Double obtenerIVA(){
        Double totalIva = 0.0;
        for (Producto p: productos){
            for (TablaVistaVenta tv:ventaProductos) {
                if(serviceProducto.obtenerIdProducto(p.getCodigo()) == tv.getIdProducto()) {
                    totalIva += ((p.getAlicuota() * p.getPrecio()) / 100) * tv.getCantidad();
                }
            }
        }
        return totalIva;
    }

    public Double obtenerTotales(int cuotas) {
        if (cuotas != 0) {
            return (Double.parseDouble(txtTotal.getText()) / cuotas);
        }
        else {
            return Double.parseDouble(txtTotal.getText());
        }
    }

    public String insertarFactura() throws SQLException {
        LocalDate localDate = fechaVentaFactura.getValue();
        Factura factura = new Factura(null,crearNumeroFactura(),false,venta.getTotalNeto(), java.sql.Date.valueOf( localDate ),evaluarLetraFactura(),serviceVenta.obtenerIdVenta());
        serviceFactura.insertarFactura(factura);
        return factura.getNumero();
    }

   /*public void asientosCuentasVentas(){
        int idFormaPago = serviceVenta.obtenerIdformaPago(txtFormaPago.getText());
        String nombreCuenta = serviceVenta.obtenerNombreCuenta(idFormaPago);
        Double total = Double.parseDouble(txtTotal.getText());
        TablaVistaAsiento formaPago = new TablaVistaAsiento(nombreCuenta,txtTotal.getText(),"",total);
        asientoCuentas.add(formaPago);
        TablaVistaAsiento ventas = new TablaVistaAsiento("Venta", "",txtTotal.getText(),total);
        asientoCuentas.add(ventas);
        TablaVistaAsiento cmv = new TablaVistaAsiento("Costo de Mercadería Vendida", obtenerCMV().toString(), "",obtenerCMV());
        asientoCuentas.add(cmv);
        TablaVistaAsiento mercaderias= new TablaVistaAsiento("Mercaderias", "",obtenerCMV().toString(),obtenerCMV());
        asientoCuentas.add(mercaderias);
   }*/

    /*public Double obtenerCMV(int cantidad, int idProducto) {
        Double cmv = 0.0;
        ArrayList<Stock> stocks = serviceProducto.obtenerStocks(idProducto);
        Stock primerStock = new Stock();
        for (Stock s: stocks) {
            if (s.getIdStock()< menorId){

            }
        }
        if (primerStock >= cantidad) {
            cmv = precioCosto * cantidad;
        } else {
            (primerStock<cantidad) {
                    cmv = precioCosto * cantidad;
            cantidad -= stock.getActual;
            serviceProducto.eliminarStock( int idStock);
            cmv += primerStock * cantidad
            }
        }
    }*/
    public void insertarAsientoVenta() {
        Asiento asiento = new Asiento(fechaActual, "venta de mercaderías en " + txtFormaPago.getText(), user.getId());
        serviceAsiento.insertarAsiento(asiento);

    }

    public String crearNumeroFactura() throws SQLException {
        String numB = "";
        if(serviceFactura.ultimaFactura() == 0){
            return "B-0002-00000000";
        }
        else{
            int numFactura = serviceFactura.ultimaFactura();
            numB = String.format("%08d", numFactura);
        }
        return "0002-" + numB;
    }



    public String evaluarLetraFactura(){
        String condicion_iva = serviceVenta.obtenerCondicionIvaCliente(venta.getIdCliente());
        String letra="";
        String ri="Responsable Inscripto";
        if(condicion_iva.trim().equals(ri)){
            letra="A";
        }
        else{
            if(condicion_iva.equals("Consumidor Final")){
                letra="B";
            }
        }
        return letra;
    }

    public String obtenerNumero(String numero){
        return numero;
    }

    @FXML
    public void accionContinuar(ActionEvent event)throws IOException {
        continuarVerRemitoFactura(event);
    }

    public void continuarVerRemitoFactura(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Ventas-View/ver-remito-factura.fxml"));
        Parent parent = fxmlLoader.load();
        setRemitoFacturaController(loadRemitoFactura(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage pagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getRemitoFacturaController().setVentana(pagoStage);
        getRemitoFacturaController().recibirDatos(numeroFac,obtenerTotalVenta(),obtenerIVA(),venta.getTotalNeto());
        getRemitoFacturaController().recibirNumRemito(numRemito);
        getRemitoFacturaController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }


    private RemitoFacturaController loadRemitoFactura(RemitoFacturaController remitoFacturaController){ return remitoFacturaController; }

    public RemitoFacturaController getRemitoFacturaController() {
        return remitoFacturaController;
    }

    public void setRemitoFacturaController(RemitoFacturaController remitoFacturaController) {
        this.remitoFacturaController = remitoFacturaController;
    }

    public String insertarRemito() throws SQLException {
        ArrayList<Remito> remitos1 = productosDeRemito(tablaVentaObservableList);
        String numeroRemito = "";
        for(Remito remito : remitos1){
            serviceRemito.insertarRemito(remito);
            numeroRemito = remito.getNumero();
        }
        return numeroRemito;
    }

    public ArrayList<Remito> productosDeRemito(ObservableList<TablaVistaVenta>  tablaVentaObservableList) throws SQLException {

        LocalDate localDate = fechaVentaFactura.getValue();
        ArrayList<Remito> remitosArray = new ArrayList<>();
        for(TablaVistaVenta tb : tablaVentaObservableList){
            Remito remito = new Remito();
            remito.setFecha(Date.valueOf(localDate));
            remito.setCantidad(tb.getCantidad());
            remito.setDescripcion(tb.getDescripcion());
            remito.setId_venta(serviceVenta.obtenerIdVenta());
            remito.setNumero(crearNumeroRemito());
            remitosArray.add(remito);
        }
        return  remitosArray;
    }

    public String crearNumeroRemito() throws SQLException {
        String numB = "";
        if(serviceRemito.ultimoRemito() == 0){
            return "0001-00000000";
        }
        else{
            int numFactura = serviceFactura.ultimaFactura();
            numB = String.format("%08d", numFactura);
        }
        return "0002-" + numB;
    }

    public ServiceRemito getServiceRemito() {
        return serviceRemito;
    }

    public void setServiceRemito(ServiceRemito serviceRemito) {
        this.serviceRemito = serviceRemito;
    }
}
