package Model.Ventas;

public class TablaGestionFactura {
    private String numeroFactura;
    private String cliente;
    private String fechaCobro;
    private double total;

    public TablaGestionFactura(){}
    public TablaGestionFactura(String numeroFactura, String cliente, String fechaCobrom, double total){
        setNumeroFactura(numeroFactura);
        setCliente(cliente);
        setFechaCobro(fechaCobrom);
        setTotal(total);
    }

    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getFechaCobro() { return fechaCobro; }
    public void setFechaCobro(String fechaCobro) { this.fechaCobro = fechaCobro; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
