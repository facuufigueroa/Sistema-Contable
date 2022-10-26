package Querys;

public class CalcularSaldoQuery {
    public static String obtenerSaldoCuenta(){ return "SELECT saldo_actual FROM cuentas WHERE idcuenta = ?"; }
    public static String sumarRestarSaldoCuenta(){ return "UPDATE cuentas SET saldo_actual = ? WHERE idcuenta = ?"; }
}
