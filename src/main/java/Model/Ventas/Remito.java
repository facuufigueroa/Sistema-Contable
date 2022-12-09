package Model.Ventas;

import java.sql.Date;

public class Remito {

    private Date fecha;
    private int cantidad;
    private String descripcion;

    private int id_venta;
    private String numero;

    public Remito(Date fecha, int cantidad, String descripcion, int id_venta, String numero) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.id_venta = id_venta;
        this.numero = numero;
    }

    public Remito() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
