package Model.Ventas;

import java.util.Date;

public class Stock {
    private int idStock;
    private int stockActual;
    private Double precioCosto;
    private Date fechaCompra;
    private int idProducto;

    public Stock() {}
    public Stock(int idStock, int stockActual, Double precioCosto, Date fechaCompra, int idProducto) {
        this.idStock = idStock;
        this.stockActual = stockActual;
        this.precioCosto = precioCosto;
        this.fechaCompra = fechaCompra;
        this.idProducto = idProducto;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public Double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(Double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
}
