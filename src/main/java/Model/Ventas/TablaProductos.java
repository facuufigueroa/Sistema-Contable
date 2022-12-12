package Model.Ventas;

import Model.Producto;

public class TablaProductos {
    private String nombre;
    private String detalle;
    private Long codigo;
    private String estado;
    private  int stock;

    private double precio;

    private double alicuota;

    public TablaProductos(String nombre, String detalle, Long codigo, String estado,double precio, int stock) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.codigo = codigo;
        this.estado = estado;
        this.precio=precio;
        this.stock = stock;
    }

    public TablaProductos(Producto producto) {
        setCodigo(producto.getCodigo());
        setNombre(producto.getNombre());
        setStock(producto.getStock());
        setDetalle(producto.getDetalle());
        setPrecio(producto.getPrecio());
        setAlicuota(producto.getAlicuota());
        if(producto.isEstado()){
            setEstado("Habilitado");
        }
        else{
            setEstado("Deshabilitado");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(double alicuota) {
        this.alicuota = alicuota;
    }
}
