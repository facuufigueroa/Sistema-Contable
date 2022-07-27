package Querys;

public class UserQuery {
    public static String insertarUsuario(){
        return "INSERT INTO usuario (email, password) VALUES (?, ?);";
    }
    public static String existeUser(String email){
        return "SELECT *\n" +
                "FROM usuario as u\n" +
                "WHERE u.email = '"+ email + "'";
    }
    public static String obtenerContrasena(){ return "SELECT password FROM usuario WHERE email = ?"; }
}