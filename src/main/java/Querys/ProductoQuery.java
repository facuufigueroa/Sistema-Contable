package Querys;

public class ProductoQuery {

    public static String insertarProducto(){
        return "INSERT INTO productos (codigo,nombre,detalle,precio_venta,estado,alicuota,stock) VALUES (?,?,?,?,?,?,?)";
    }

    public static String listarProductosHabilitados(){
        return "SELECT * FROM productos as p WHERE p.estado=true ORDER BY p.codigo ASC";
    }

}
