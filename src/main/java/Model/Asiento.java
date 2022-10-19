package Model;
import java.time.LocalDate;

public class Asiento {
    private LocalDate fecha;

    private String detalle;

    private int usuario;

    private int codigoAsiento;

    public Asiento(LocalDate fecha,String detalle, int usuario) {
        this.detalle = detalle;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getCodigoAsiento() {
        return codigoAsiento;
    }

    public void setCodigoAsiento(int codigoAsiento) {
        this.codigoAsiento = codigoAsiento;
    }
}
