package Querys.Ventas;

import DataBase.ConexionBD;

public class ClienteQuery {
    public static String existeDni(){ return "SELECT dni FROM clientes WHERE dni = ?"; }
    public static String existeCuit(){ return "SELECT cuit FROM clientes WHERE cuit = ?"; }
    public static String getPersonaById(){ return "SELECT * FROM clientes WHERE email = ? LIMIT 1"; }
    public static String getListadoPersonas(){
        return "SELECT dni, cuit, nombre, apellido, email, direccion, telefono, razon_social, t.tipo AS tipo, estado\n" +
               "FROM clientes\n" +
               "INNER JOIN tipo_persona AS t ON t.idtipopersona = clientes.id_tipo_persona;";
    }

    public static String listarClientesHabilitados(){
        return "SELECT * FROM clientes as c WHERE c.estado = true ORDER BY c.nombre ASC";
    }

    public static String obtenerIdTipoPersona(String nombre){
        return "SELECT idtipopersona FROM tipo_persona WHERE nombre='"+nombre+"'";
    }

    public static String insertarPersona(){
        return "INSERT INTO clientes (dni, cuit, nombre, apellido, email, direccion, telefono, razon_social, estado, id_tipo_persona) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public static String modificarPersonaJuridica(){
        return    "UPDATE clientes "
                + "SET  razon_social = ?, email = ?, direccion = ?, telefono = ?"
                + "WHERE cuit = ?"; //
    }
    public static String modificarPersonaFisica(){
        return    "UPDATE clientes "
                + "SET nombre = ?, apellido = ?, email = ?, direccion = ?, telefono = ? "
                + "WHERE cuit = ?";
    }
    public static String modificarEstado(){
        return    "UPDATE clientes "
                + "SET estado = ? "
                + "WHERE cuit = ?";
    }

    public static String obtenerIdCliente(String nombre){
        return "SELECT idcliente FROM clientes WHERE nombre='"+nombre+"'";
    }

    public static String obtenerCondicionIva(){
        return "SELECT c.condicion_iva FROM clientes AS c WHERE c.idcliente=?";
    }

    public static String obtenerNombreCliente(){
        return "SELECT c.nombre FROM clientes AS c WHERE c.idcliente=?";
    }
}
