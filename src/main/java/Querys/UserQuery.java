package Querys;

public class UserQuery {
    public static String insertarUsuario() { return "INSERT INTO usuarios (nombre, apellido, email, contrasena) VALUES (?, ?, ?, ?);"; }
    public static String insertarRol(){ return "INSERT INTO roles (nombre) VALUES (?)"; }
    public static String insertarUsuarioRol(){ return  "INSERT INTO usuario_rol (usuario, rol) VALUES (?, ?)"; }
    public static String existeObjeto(String tabla, String atributo){
        return "SELECT * FROM "  + tabla +  " WHERE " + atributo + " =  "  + "?";
    }
    public static String obtenerUsuarioPorEmail(){ return "SELECT * FROM usuarios WHERE email = ?"; }
    public static String existeUser(String email){
        return "SELECT *\n" +
                "FROM usuarios as u\n" +
                "WHERE u.email = '"+ email + "'";
    }
    public static String obtenerContrasena(){ return "SELECT contrasena FROM usuarios WHERE email = ?"; }

}