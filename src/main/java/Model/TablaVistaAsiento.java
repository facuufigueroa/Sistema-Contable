package Model;

public class TablaVistaAsiento {

    private String nombreCuenta;
    private double debe;
    private double haber;

    private double saldo;

    public TablaVistaAsiento(String nombreCuenta, double debe, double haber, double saldo) {
        this.nombreCuenta = nombreCuenta;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
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
}
