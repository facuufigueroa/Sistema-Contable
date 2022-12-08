package Model.Ventas;

import Model.Producto;

import java.util.ArrayList;

public class Venta {
    private int idCliente;
    private ArrayList<Producto> productos;
    private ArrayList<TablaVistaVenta> ventaProductos;
    private Double totalBruto;
    private Double totalNeto;
    private Double totales;
    private int formaPago;

    private int idUsuario;
    private int cuotas;
    private static Venta venta;

    public Venta(){}

    public Venta(int idCliente, ArrayList<Producto> productos, Double totalBruto, Double totalNeto, Double totales, int formaPago) {
        this.idCliente = idCliente;
        this.productos = productos;
        this.totalBruto = totalBruto;
        this.totalNeto = totalNeto;
        this.totales = totales;
        this.formaPago = formaPago;
    }

    public Venta(int idCliente, Double totalBruto, Double totalNeto, Double totales, int formaPago, int idUsuario) {
        this.idCliente = idCliente;
        this.totalBruto = totalBruto;
        this.totalNeto = totalNeto;
        this.totales = totales;
        this.formaPago = formaPago;
        this.idUsuario = idUsuario;
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

    public int getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(int formaPago) {
        this.formaPago = formaPago;
    }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getCuotas() { return cuotas; }

    public void setCuotas(int cuotas) { this.cuotas = cuotas; }
}
