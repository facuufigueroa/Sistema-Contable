package Model.Ventas;

import Model.Producto;
import Model.TablaVistaAsiento;

import java.util.ArrayList;

public class Venta {
    private int idCliente;
    private ArrayList<Producto> productos;

    private ArrayList<TablaVistaVenta> ventaProductos;
    private Double totalBruto;
    private Double totalNeto;
    private Double totales;
    private int factura;
    private int remito;
    private String formaPago;
    private static Venta venta;

    public Venta(){}

    public Venta(int idCliente, ArrayList<Producto> productos, Double totalBruto, Double totalNeto, Double totales, int factura, int remito, String formaPago) {
        this.idCliente = idCliente;
        this.productos = productos;
        this.totalBruto = totalBruto;
        this.totalNeto = totalNeto;
        this.totales = totales;
        this.factura = factura;
        this.remito = remito;
        this.formaPago = formaPago;
    }

    public Double obtenerTotalVenta(){
        Double total = 0.0;
        for(TablaVistaVenta producto : venta.getVentaProductos()){
            total += producto.getPrecioTotal();
        }
        return total;
    }
    public Double obtenerIVA(){
        Double totalIva = 0.0;
        for (Producto p: getProductos()){
            totalIva = (p.getAlicuota() * p.getPrecio())/100;
        }
        return totalIva;
    }

    public static Venta getInstance(){
        if(venta == null){
            venta = new Venta();
        }
        return venta;
    }

    public ArrayList<TablaVistaVenta> getVentaProductos() {
        return ventaProductos;
    }

    public void setVentaProductos(ArrayList<TablaVistaVenta> ventaProductos) {
        this.ventaProductos = ventaProductos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int cliente) {
        this.idCliente = cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Double getTotalBruto() { return totalBruto; }

    public void setTotalBruto(Double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(Double totalNeto) {
        this.totalNeto = totalNeto;
    }

    public Double getTotales() {
        return totales;
    }

    public void setTotales(Double totales) {
        this.totales = totales;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public int getRemito() {
        return remito;
    }

    public void setRemito(int remito) {
        this.remito = remito;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
}
