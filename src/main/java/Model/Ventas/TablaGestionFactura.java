package Model.Ventas;

public class TablaGestionFactura {
    private String numeroFactura;
    private String cliente;
    private String fecha;
    private double total;
    private int idFactura;

    public TablaGestionFactura(){}
    public TablaGestionFactura(String numeroFactura, String cliente, String fecha, double total, int idFactura){
        setNumeroFactura(numeroFactura);
        setCliente(cliente);
        setFecha(fecha);
        setTotal(total);
        setIdFactura(idFactura);
    }

    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getFecha() { return fecha; }
    public void setFecha(String fechaCobro) { this.fecha = fechaCobro; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public int getIdFactura() { return idFactura; }
    public void setIdFactura(int idFactura) { this.idFactura = idFactura; }
}
