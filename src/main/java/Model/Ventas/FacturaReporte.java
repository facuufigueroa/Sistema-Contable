package Model.Ventas;

public class FacturaReporte {
    private String numero;
    private Double totalBruto;
    private Integer alicuota;
    private Double totalNeto;

    public FacturaReporte(){}
    public FacturaReporte(String numero, Double totalBruto, Integer alicuota, Double totalNeto) {
        setNumero(numero);
        setTotalBruto(totalBruto);
        setAlicuota(alicuota);
        setTotalNeto(totalNeto);
    }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public Double getTotalBruto() { return totalBruto; }
    public void setTotalBruto(Double totalBruto) { this.totalBruto = totalBruto; }
    public Integer getAlicuota() { return alicuota; }
    public void setAlicuota(Integer alicuota) { this.alicuota = alicuota; }
    public Double getTotalNeto() { return totalNeto; }
    public void setTotalNeto(Double totalNeto) { this.totalNeto = totalNeto; }
}
