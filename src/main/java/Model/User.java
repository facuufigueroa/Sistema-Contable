package Model;
public class User {
    private String email;
    private String contrasena;

    private String nombre;

    private String apellido;

    public User(){}

    public User(String email, String password) {
        setEmail(email);
        setContrasena(password);
    }

    public User(String nombre, String apellido, String email, String contrasena) {
       setNombre(nombre);
       setApellido(apellido);
       setEmail(email);
       setContrasena(contrasena);
    }
    public User(User usuario){
        setNombre(usuario.getNombre());
        setApellido(usuario.getApellido());
        setEmail(usuario.getEmail());
        setContrasena(usuario.getContrasena());
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
