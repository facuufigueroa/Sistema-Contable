package Querys;

public class UserQuery {
    public static String insertarUsuario(){
        return "INSERT INTO usuarios (email, contraseña) VALUES (?, ?);";
    }
    public static String existeUser(String email){
        return "SELECT *\n" +
                "FROM usuarios as u\n" +
                "WHERE u.email = '"+ email + "'";
    }
    public static String obtenerContrasena(){ return "SELECT contraseña FROM usuarios WHERE email = ?"; }
}