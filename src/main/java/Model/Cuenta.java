package Model;

public class Cuenta {

    public String codigo;
    public String nombre;
    public String recibe_salgo;
    public String tipo;

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

    public String getRecibe_salgo() {
        return recibe_salgo;
    }

    public void setRecibe_salgo(String recibe_salgo) {
        this.recibe_salgo = recibe_salgo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
