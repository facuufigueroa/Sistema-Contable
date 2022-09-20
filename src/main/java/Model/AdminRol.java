package Model;

import Controller.CuentaController;
import Controller.CuentaDeshabilitadaController;
import Controller.MainController;

public class AdminRol extends Roles{
    public AdminRol(){
        super();
        setRol("admin");
    }
    public AdminRol(User user){
        super(user);
        setRol("admin");
    }
    public AdminRol(User user, String rol) {
        super(user, "admin");
    }

    @Override
    public void permisos(MainController controller) {
        Utilidades.mostrarBoton(controller.getBtnCrearUsuario());
        Utilidades.habilitarBoton(controller.getBtnCrearUsuario());
    }

    @Override
    public void permisosAsiento(CuentaController controller) {
        Utilidades.habilitarBoton(controller.getBtnDeshabilitarCuenta());
    }

    @Override
    public AdminRol tipoRol(User usuario, String rol) {
        if (rol.equals("admin")){
            return new AdminRol(usuario);
        }
        return null;
    }
    public void permisosCuentasDeshabilitadas(CuentaDeshabilitadaController controller) {
        Utilidades.habilitarBoton(controller.getBtnHabilitarCuenta());
    }
}
