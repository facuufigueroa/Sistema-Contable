package Controller;

import Model.ViewFuntionality;
import Reportes.ReporteFactura;
import Reportes.ReporteRemito;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RemitoFacturaController  extends ViewFuntionality {


    private String numFactura;
    private double subtotal;
    private double iva;
    private double total;

    private String numeroRemito;

    ReporteFactura reporteFactura = new ReporteFactura();
    ReporteRemito reporteRemito = new ReporteRemito();

    private HomeVentasController homeVentasController;

    public void recibirDatos(String numFactura,double subtotal,double iva,double total){
        setNumFactura(numFactura);
        setSubtotal(subtotal);
        setIva(iva);
        setTotal(total);
    }

    public void setearDatosEnCero(){
        setNumFactura(null);
        setSubtotal(0);
        setIva(0);
        setTotal(0);
        setNumeroRemito(null);
    }

    public void recibirNumRemito(String numeroRemito){
        setNumeroRemito(numeroRemito);
    }


    @FXML
    private Button btnVerRemito;
    @FXML
    private Button btnVerFactura;
    @FXML
    private Button btnFinalizar;

    @FXML
    public void accionVerRemito(){
        reporteRemito.loadRemito(numeroRemito);
    }
    @FXML
    public void accionVerFactura(){
        reporteFactura.loadFactura(subtotal,iva,total,numFactura);
    }
    @FXML
    public void accionFinalizar(ActionEvent event) throws IOException {
        setearDatosEnCero();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(loadHomeVentas(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage formaPagoStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeVentasController().setVentana(formaPagoStage);
        getHomeVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

    }

    private HomeVentasController loadHomeVentas(HomeVentasController homeController){ return homeController; }


    public void hideStage(){ getVentana().hide(); }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNumeroRemito() {
        return numeroRemito;
    }

    public void setNumeroRemito(String numeroRemito) {
        this.numeroRemito = numeroRemito;
    }

    public HomeVentasController getHomeVentasController() {
        return homeVentasController;
    }

    public void setHomeVentasController(HomeVentasController homeVentasController) {
        this.homeVentasController = homeVentasController;
    }
}
