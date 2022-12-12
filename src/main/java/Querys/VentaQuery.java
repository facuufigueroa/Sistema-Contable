package Querys;

public class VentaQuery {
    public static String insertarVenta(){
        return "INSERT INTO ventas (total_bruto, total_neto,totales,id_forma_pago,id_cliente,id_usuario) VALUES (?,?,?,?,?,?)";
    }

    public static String insertarVenta_producto(){
        return "INSERT INTO venta_producto (id_venta,id_producto, cantidad, precio_unitario, importe) VALUES (?,?,?,?,?)";
    }

    public static String obtenerFormaPago(int id_forma_pago) {
        return "SELECT forma_pago FROM forma_pago WHERE forma_pago.idformapago='"+id_forma_pago+"'";
    }

    public static String obtenerIdFormaPago(String formaPago) {
        return "SELECT idformapago FROM forma_pago  as fp WHERE fp.forma_pago ='"+formaPago+"'";
    }

    public static String obtenerIdVenta() {
        return "SELECT MAX(idventa) FROM ventas";
    }

    public static String obtenerCondicionIvaCliente(int idCliente) {
        return "SELECT condicion_iva FROM clientes as c WHERE c.idcliente="+idCliente;
    }

    public static String obtenerNombreCuenta(int formaPago) {
        return "SELECT c.nombre FROM cuentas AS c INNER JOIN forma_pago_cuenta AS fpc ON c.idcuenta = fpc.id_cuenta WHERE fpc.id_forma_pago="+formaPago;
    }
}
