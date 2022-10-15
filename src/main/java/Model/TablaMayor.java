package Model;


public class TablaMayor {
    private int idAsiento;
    private String detalle;
    private String debe;
    private String haber;
    private double saldo;

    public TablaMayor(int idAsiento, String detalle, String debe, String haber, double saldo){
        setIdAsiento(idAsiento);
        setDetalle(detalle);
        setDebe(debe);
        setHaber(haber);
        setSaldo(saldo);
    }
    public TablaMayor(int idAsiento, String detalle){
        setIdAsiento(idAsiento);
        setDetalle(detalle);
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDebe() {
        return debe;
    }

    public void setDebe(String debe) {
        this.debe = debe;
    }

    public String getHaber() {
        return haber;
    }

    public void setHaber(String haber) {
        this.haber = haber;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return getIdAsiento() + " " + getDetalle() + " " + getDebe() + " " + getHaber() + getSaldo() + "\n";
     }
}
