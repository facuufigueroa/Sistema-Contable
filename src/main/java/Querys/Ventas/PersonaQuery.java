package Querys.Ventas;

public class PersonaQuery {
    public static String getPersonaById(){ return "SELECT * FROM clientes WHERE email = ? LIMIT 1"; }
    public static String getListadoPersonas(){ return "SELECT * FROM clientes"; }
}
