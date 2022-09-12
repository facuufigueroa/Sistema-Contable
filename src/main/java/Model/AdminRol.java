package Model;

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
    public void addRol() {

    }

    @Override
    public void removeRol() {

    }

    @Override
    public void permisos(MainController controller) {
        Utilidades.mostrarBoton(controller.getBtnCrearUsuario());
        Utilidades.habilitarBoton(controller.getBtnCrearUsuario());
    }
    @Override
    public AdminRol tipoRol(User usuario, String rol) {
        if (rol.equals("admin")){
            return new AdminRol(usuario);
        }
        return null;
    }
}
