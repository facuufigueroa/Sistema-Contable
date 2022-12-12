package Querys;

public class RolesQuery {
    public static String listarRoles(){ return "SELECT nombre FROM roles"; }
    public static String obtenerIdUser(){ return "SELECT idusuario FROM usuarios WHERE email = ? LIMIT 1"; }
    public static String obtenerIdRol(){ return "SELECT idrol FROM roles WHERE nombre = ? LIMIT 1"; }
    public static String asignarRol(){ return "INSERT INTO usuario_rol (usuario, rol) VALUES (? , ?)"; }
    public static String obtenerRolUsuarioPorEmail(){
        return    "SELECT roles.idrol, roles.nombre "
                + "FROM usuario_rol "
                + "INNER JOIN usuarios AS usuarios ON usuarios.idusuario = usuario_rol.usuario "
                + "INNER JOIN roles AS roles ON roles.idrol = usuario_rol.rol "
                + "WHERE usuarios.email = ? LIMIT 1";
    }

    public static String obtenerRolPorEmail(String email){
        return "SELECT r.nombre\n" +
                "FROM roles as r\n" +
                "inner join usuario_rol as ur on ur.rol=r.idrol\n" +
                "inner join usuarios as u on u.idusuario = ur.usuario\n" +
                "where u.email='"+email+"'";
    }

}
