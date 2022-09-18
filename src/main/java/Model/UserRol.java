package Model;
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
    public void addRol() {

    }

    @Override
    public void removeRol() {

    }

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
}
