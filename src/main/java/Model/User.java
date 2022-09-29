package Model;
public class User {

    private int id;
    private String email;
    private String contrasena;

    private static User user;
    private String nombre;

    private String apellido;


    public User(){}

    public static User getInstance(){
        if(user == null){
            user = new User();
        }
        return user;
    }
    public User(String email, String password) {
        setEmail(email);
        setContrasena(password);
    }

    public User(String email, String contrasena, String nombre, String apellido) {
        this.email = email;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public User(int id, String nombre, String apellido, String email, String contrasena) {
        setId(id);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

