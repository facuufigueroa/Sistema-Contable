package Model;

public abstract class Roles {
    private User user;
    private Roles rol;

    public Roles(){}
    public Roles(User user, Roles rol) {
        setUser(user);
        setRol(rol);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Roles getRol() { return rol; }
    public void setRol(Roles rol) { this.rol = rol; }

    public abstract void addRol();
    public abstract void removeRol();
}
