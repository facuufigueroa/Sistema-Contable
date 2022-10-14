package Querys;

public class LibroMayorQuery {

    public static String obtenerIdAsiento(){ return "SELECT asiento FROM asientos_cuentas WHERE cuenta = ?";}

    public static String obtenerIdAsientoPorFecha(){
        return "SELECT asiento \n" +
                "FROM asientos_cuentas \n" +
                "INNER JOIN asientos AS a ON a.idasiento = asientos_cuentas.asiento\n" +
                "WHERE cuenta = ?" +
                "AND a.fecha BETWEEN ? AND ?";
    }

    public static String obtenerDetallePorId(){ return "SELECT detalle FROM asientos WHERE idasiento = ?"; }

    public static String obtenerDebePorId() { return "SELECT debe FROM asientos_cuentas WHERE asiento = ?"; }
    public static String obtenerHaberPorId() { return "SELECT haber FROM asientos_cuentas WHERE asiento = ?"; }
    public static String obtenerSaldoPorId() { return "SELECT saldo FROM asientos_cuentas WHERE asiento = ?"; }
    public static String obtenerAsientosPorFecha(){ return "SELECT idasiento, detalle FROM asientos WHERE idasiento = ? "
            +"AND fecha BETWEEN ? AND ? ";
    }
}
