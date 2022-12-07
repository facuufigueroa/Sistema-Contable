package Model;

import java.util.ArrayList;

public class ProductoAgregado {
    private Long codigo;
    private String nombre;
    private int cantidad;

    private int stock;

    public ProductoAgregado() {
    }

    public ProductoAgregado (Long codigo, String nombre,int cantidad, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
    }
    public Long getCodigo() { return codigo; }

    public void setCodigo(Long codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public int getStock() {return stock;}

    public void setStock(int stock) {this.stock = stock;}
}
