package Querys;

public class RolesQuery {
    public static String obtenerIdUser(){ return "SELECT idusuario FROM usuarios WHERE email = ? LIMIT 1"; }
    public static String obtenerIdRol(){ return "SELECT idrol FROM roles WHERE nombre = ? LIMIT 1"; }
    public static String asignarRol(){ return "INSERT INTO usuario_rol (usuario, rol) VALUES (?, ?)"; }
    public static String obtenerRol(){ return "SELECT nombre FROM roles"; }

}
