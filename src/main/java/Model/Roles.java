package Model;

import Controller.CuentaController;
import Controller.CuentaDeshabilitadaController;
import Controller.MainController;

public abstract class Roles {
    private User user;
    private String rol;

    public Roles(){}
    public Roles(User user){ setUser(user);}
    public Roles(User user, String rol) {
        setUser(user);
        setRol(rol);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getRol() { return rol; }
    protected void setRol(String rol) { this.rol = rol; }

    public String nombre(){ return getUser().getNombre(); }
    public String apellido(){ return getUser().getApellido(); }
    public String email(){ return getUser().getEmail(); }


    public abstract void permisos(MainController controller);
    public abstract void permisosAsiento(CuentaController controller);
    public abstract void permisosCuentasDeshabilitadas(CuentaDeshabilitadaController controller);
    public abstract Roles tipoRol(User usuario, String rol);

    @Override
    public String toString() { return getRol(); }
}
