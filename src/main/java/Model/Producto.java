package Model;

public class Producto {

    private Long codigo;
    private String nombre;
    private String detalle;
    private Double precio;
    private Double alicuota;
    private boolean estado;
    private int stock;

    public Producto(long codigo, String nombre, String detalle, Double precio, Double alicuota) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.detalle = detalle;
        this.precio = precio;
        this.alicuota = alicuota;
        this.estado=true;
    }

    public Producto(String nombre, String detalle, Double precio,double alicuota) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.precio = precio;
        this.alicuota=alicuota;
    }

    public Producto(Long codigo, String nombre, String detalle, Double precio, Double alicuota, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.detalle = detalle;
        this.precio = precio;
        this.alicuota = alicuota;
        this.estado = estado;
    }

    public Producto() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(Double alicuota) {
        this.alicuota = alicuota;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock;}
}
