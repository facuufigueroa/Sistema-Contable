package Model;

public class Cuenta {

    public String codigo;
    public String nombre;
    public String recibe_saldo;
    public String tipo;

    public Boolean estado;

    public double saldo_actual;

    public Cuenta() {
        this.estado = true;
        this.saldo_actual=0;
    }

    public double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRecibe_saldo() {
        return recibe_saldo;
    }

    public void setRecibe_saldo(String recibe_saldo) {
        this.recibe_saldo = recibe_saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
