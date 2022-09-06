package Querys;

public class CuentaQuery {

    public static String listarCuentasHabilitadas(){
        return "SELECT * FROM cuentas as c WHERE c.estado=true";
    }

    public static String listarCuentasDeshabilitadas(){
        return "SELECT * FROM cuentas as c WHERE c.estado=false";
    }

    public static String habilitarCuenta(String codigo){
        return "UPDATE cuentas SET estado = true WHERE codigo ="+codigo;
    }

    public static String deshabilitarCuenta(String codigo){
        return "UPDATE cuentas SET estado = false WHERE codigo ='"+codigo+"'";
    }

}
