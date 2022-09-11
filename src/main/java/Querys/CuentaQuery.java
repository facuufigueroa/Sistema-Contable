package Querys;

import Model.Cuenta;

public class CuentaQuery {

    public static String listarCuentasHabilitadas(){
        return "SELECT * FROM cuentas as c WHERE c.estado=true ORDER BY c.codigo ASC";
    }

    public static String listarCuentasDeshabilitadas(){
        return "SELECT * FROM cuentas as c WHERE c.estado=false ORDER BY c.codigo ASC";
    }

    public static String habilitarCuenta(String codigo){
        return "UPDATE cuentas SET estado = true WHERE codigo ='"+codigo+"'";
    }

    public static String deshabilitarCuenta(String codigo){
        return "UPDATE cuentas SET estado = false WHERE codigo ='"+codigo+"'";
    }

    public static String insertarCuenta(){
        return "INSERT INTO cuentas (codigo,nombre,recibe_saldo,tipo,estado) VALUES (?,?,?,?,?)";
    }

    public static String existeCodigoCuenta(String codigo){
        return "SELECT * FROM cuentas as c WHERE c.codigo='"+codigo+"'";
    }

    public static String updateCuentas(){
        return "SELECT * FROM cuentas";
    }

}
