package Querys;

public class UserQuery {

    public static String insertarUsuario() { return "INSERT INTO usuarios (nombre, apellido, email, contraseña) VALUES (?, ?, ?, ?);"; }
    public static String asignarRol(){ return "INSERT INTO usuario_rol (usuario,rol) VALUES (?,?)"; }
    public static String obtenerRol(){ return "SELECT nombre FROM roles"; }
    public static String obtenerIdRolNombreRol(){ return "SELECT idrol FROM roles WHERE nombre = ?"; }

    public static String obtenerUsuarioRol(){
        return   " SELECT usuarios.nombre, usuarios.apellido, usuarios.email, roles.nombre as rol "
               + " FROM usuario_rol"
               + " INNER JOIN usuarios ON usuarios.idusuario = usuario "
               + " INNER JOIN roles ON roles.idrol = rol";
    }
    public static String obtenerUsuarios(){
        return    " SELECT usuarios.nombre, usuarios.apellido, usuarios.email, roles.nombre as rol, usuarios.contraseña"
                + " FROM usuario_rol"
                + " INNER JOIN usuarios ON usuarios.idusuario = usuario"
                + " INNER JOIN roles ON roles.idrol = rol";
                //+ " WHERE usuarios.email = ?";
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

}