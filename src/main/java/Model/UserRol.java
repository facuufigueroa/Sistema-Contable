package Model;
import Controller.AsientoController;
import Controller.CuentaController;
import Controller.CuentaDeshabilitadaController;
import Controller.MainController;

public class UserRol extends Roles{
    public UserRol(){
        super();
        setRol("usuario");
    }
    public UserRol(User user){
        super(user);
        setRol("usuario");
    }
    public UserRol(User user, String rol) { super(user, rol); }


    @Override
    public void permisos(MainController controller) {
        Utilidades.deshabilitarBoton(controller.getBtnCrearUsuario());
        Utilidades.ocultarBoton(controller.getBtnCrearUsuario());
    }

    @Override
    public UserRol tipoRol(User user,String rol) {
        if (rol.equals("usuario")){
            return new UserRol(user);
        }
        return null;
    }
    public void permisosAsiento(CuentaController controller){
        Utilidades.deshabilitarBoton(controller.getBtnDeshabilitarCuenta());
    }
    public void permisosCuentasDeshabilitadas(CuentaDeshabilitadaController controller) {
        Utilidades.deshabilitarBoton(controller.getBtnHabilitarCuenta());
    }
}
