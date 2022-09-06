package Model;

public class Cuenta {

    public String codigo;
    public String nombre;
    public String recibe_saldo;
    public String tipo;

    public Boolean estado;

    public Cuenta() {
        this.estado = true;
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
