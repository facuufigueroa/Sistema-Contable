package Model;

public class TablaVistaAsiento {

    private String nombreCuenta;
    private String debe;
    private String haber;

    private double saldo;

    public TablaVistaAsiento(String nombreCuenta, String debe, String haber, double saldo) {
        this.nombreCuenta = nombreCuenta;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
    }

    public TablaVistaAsiento() {

    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
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
}
