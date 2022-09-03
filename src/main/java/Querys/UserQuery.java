package Querys;

public class UserQuery {
    public static String insertarUsuario() { return "INSERT INTO usuarios (nombre, apellido, email, contrasena) VALUES (?, ?, ?, ?);"; }
    public static String insertarRol(){ return "INSERT INTO roles (nombre) VALUES (?)"; }
    public static String insertarUsuarioRol(){ return  "INSERT INTO usuario_rol (usuario, rol) VALUES (?, ?)"; }
    public static String existeUser(String email){
        return "SELECT *\n" +
                "FROM usuarios as u\n" +
                "WHERE u.email = '"+ email + "'";
    }
    public static String obtenerContrasena(){ return "SELECT contrasena FROM usuarios WHERE email = ?"; }

}