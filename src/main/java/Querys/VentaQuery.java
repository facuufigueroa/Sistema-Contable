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
        return "SELECT idformapago FROM forma_pago WHERE UPPER (forma_pago.forma_pago) ='"+formaPago+"'";
    }

    public static String obtenerIdVenta() {
        return "SELECT MAX(idventa) FROM ventas";
    }

}
