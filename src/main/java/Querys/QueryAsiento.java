package Querys;

public class QueryAsiento {

    public static String insertarAsiento(){ return  "INSERT INTO asientos (fecha, detalle, usuario) "
            + "VALUES (CURRENT_DATE, ?, ?)"; }

    public static String insertarCuentaAsiento(){
        return "INSERT INTO asientos_cuentas (debe,haber,saldo,asiento,cuenta) VALUES (?,?,?,?,?)";
    }

    public static String obtenerIdCuenta(String nombre){
        return "SELECT c.idcuenta FROM cuentas as c WHERE c.nombre='"+nombre+"'";
    }

    public static String obtenerIdAsiento(){
        return "SELECT idasiento FROM asientos ORDER BY idasiento DESC LIMIT 1";
    }

    public static String obtenerNombreCuenta(){
        return "SELECT c.nombre FROM cuentas as c WHERE c.idcuenta=?";
    }

}
