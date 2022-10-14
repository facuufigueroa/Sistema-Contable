package Model;

import java.time.LocalDate;

public class TablaMayor {
    private int idAsiento;
    private String detalle;
    private double debe;
    private double haber;
    private double saldo;

    public TablaMayor(int idAsiento, String detalle, double debe, double haber, double saldo){
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

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
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
