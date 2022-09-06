package Model;
public class User {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;

    public User(){}
    public User(String email, String contrasena){
        setEmail(email);
        setContrasena(contrasena);
    }
    public User(String nombre, String apellido, String email, String contrasena) {
        setNombre(nombre);
        setApellido(apellido);
        setEmail(email);
        setContrasena(contrasena);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() { return "{{\n" + "Nombre: " + getNombre() + ", " + getApellido() + "\nEmail: " + getEmail() + "\nContrasena: " + getContrasena() + "\n}}"; }
}
