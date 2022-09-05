package Model;

import Test.testController;

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
    public void permisos() {

    }

    @Override
    public void restricciones(testController testController) {
        sacarPermisosUser(testController);
    }

    public void sacarPermisosUser(testController test){
        test.deshabilitarBoton(test.btn3);
        test.deshabilitarPanel(test.panelRojo);
        test.deshabilitarFecha(test.fecha);
        test.habilitarPanel(test.panelAzul);
    }
}
