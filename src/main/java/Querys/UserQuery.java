package Querys;

public class UserQuery {
    public static String insertarUsuario(){
        return "INSERT INTO usuario (email, password) VALUES (?, ?);";
    }
}
