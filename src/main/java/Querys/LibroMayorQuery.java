package Querys;

public class LibroMayorQuery {

    public static String obtenerIdAsiento(){ return "SELECT asiento FROM "; }

    public static String obtenerDetallePorId(){ return "SELECT detalle FROM asientos WHERE idasiento = ?"; }

    public static String obtenerDebePorId() { return "SELECT debe FROM asientos_cuentas WHERE idasiento = ?"; }
    public static String obtenerHaberPorId() { return "SELECT debe FROM asientos_cuentas WHERE idasiento = ?"; }
    public static String obtenerSaldoPorId() { return "SELECT saldo FROM asientos_cuentas WHERE idasiento = ?"; }
}
