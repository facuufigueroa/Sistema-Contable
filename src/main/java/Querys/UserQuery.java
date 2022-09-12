package Querys;

public class UserQuery {

    public static String insertarUsuario() { return "INSERT INTO usuarios (nombre, apellido, email, contraseña) VALUES (?, ?, ?, ?);"; }
    public static String asignarRol(){ return "INSERT INTO usuario_rol (usuario,rol) VALUES (?,?)"; }
    public static String obtenerRol(){ return "SELECT nombre FROM roles"; }


    public static String obtenerUsuarioRol(){
        return   " SELECT usuarios.nombre, usuarios.apellido, usuarios.email, roles.nombre as rol "
               + " FROM usuario_rol"
               + " INNER JOIN usuarios ON usuarios.idusuario = usuario "
               + " INNER JOIN roles ON roles.idrol = rol";
    }
    public static String obtenerUsuarioRolPorEmail(){
        return    " SELECT usuarios.nombre, usuarios.apellido, usuarios.email, roles.nombre as rol"
                + " FROM usuario_rol"
                + " INNER JOIN usuarios ON usuarios.idusuario = usuario"
                + " INNER JOIN roles ON roles.idrol = rol"
                + " WHERE usuarios.email = ?";
    }

    public static String existeObjeto(String tabla, String atributo) {
        return "SELECT * FROM " + tabla + " WHERE " + atributo + " =  " + "?";}


        public static String existeUser (String email){
            return "SELECT *\n" +
                    "FROM usuarios as u\n" +
                    "WHERE u.email = '" + email + "'";
        }
        public static String obtenerContrasena () {
            return "SELECT contraseña FROM usuarios WHERE email = ?";
        }

    public static String obtenerUsuarioPorEmail(){ return "SELECT * FROM usuarios WHERE email = ?"; }

    /*Consultas armada para mostrar el usuario en sesion en menu principal, falta implementar instancia del usuario

    public static String traerUsuarioConRol(String email){
        return "SELECT u.email\n" +
                "FROM usuarios AS u\n" +
                "WHERE u.email = '" + email + "'";
    }

    public static String traerRolesUsuario(String email){
        return "SELECT r.nombre\n" +
                "FROM roles r \n" +
                "INNER JOIN usuario_rol ur ON ur.rol = r.idrol\n" +
                "INNER JOIN usuarios u ON ur.usuario = u.idusuario\n" +
                "WHERE u.email = '"+email +"'";
    }
    */


}