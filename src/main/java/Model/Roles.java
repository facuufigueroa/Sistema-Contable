package Model;

import Test.testController;

public abstract class Roles {
    private User user;
    private String rol;

    public Roles(){}
    public Roles(User user){ setUser(user); }
    public Roles(User user, String rol) {
        setUser(user);
        setRol(rol);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public abstract void addRol();
    public abstract void removeRol();
    public abstract void permisos();
    public abstract void restricciones(testController test);
}
