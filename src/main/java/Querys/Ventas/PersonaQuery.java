package Querys.Ventas;

public class PersonaQuery {
    public static String getPersonaById(){ return "SELECT * FROM clientes WHERE email = ? LIMIT 1"; }
    public static String getListadoPersonas(){
        return "SELECT dni, cuit, nombre, apellido, email, direccion, telefono, razon_social, t.tipo AS tipo, estado\n" +
               "FROM clientes\n" +
               "INNER JOIN tipo_persona AS t ON t.idtipopersona = clientes.id_tipo_persona;";
    }
    public static String insertarPersona(){
        return "INSERT INTO clientes (dni, cuit, nombre, apellido, email, direccion, telefono, razon_social, estado, id_tipo_persona) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
