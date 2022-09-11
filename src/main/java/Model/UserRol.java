package Model;

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

}
