package Model;

public class AsientoCuenta {

    private int asiento;
    private int cuenta;
    private double debe;
    private double haber;
    private double saldo;


    public AsientoCuenta(int asiento, int cuenta, double debe, double haber, double saldo) {
        this.asiento = asiento;
        this.cuenta = cuenta;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
    }

    public AsientoCuenta(int asiento, int cuenta, double debe, double haber) {
        this.asiento = asiento;
        this.cuenta = cuenta;
        this.debe = debe;
        this.haber = haber;

    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
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
