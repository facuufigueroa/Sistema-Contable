package Model;

import Test.testController;

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
    public void permisos() {

    }

    @Override
    public void restricciones(testController test) {
        test.deshabilitarPanel(test.panelAzul);
        test.habilitarPanel(test.panelRojo);
        test.habilitarFecha(test.fecha);
        test.habilitarBoton(test.btn3);
    }
}
