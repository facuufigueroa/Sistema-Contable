package Querys;

import Model.Producto;
import net.sf.jasperreports.crosstabs.fill.JRPercentageCalculatorFactory;

public class ProductoQuery {

    public static String insertarProducto(){
        return "INSERT INTO productos (codigo,nombre,detalle,precio_venta,estado,alicuota) VALUES (?,?,?,?,?,?)";
    }

    public static String listarProductos(){
        return "SELECT * FROM productos as p ORDER BY p.codigo ASC";
    }

    public static String listarProductosHabilitados(){
        return "SELECT * FROM productos as p WHERE p.estado = true ORDER BY p.codigo ASC";
    }

    public static String obtenerAlicuota(Long codigo){
        return "SELECT alicuota FROM productos as p WHERE p.codigo="+codigo;
    }

    public static String existeProducto(Long codigo){
        return "SELECT * FROM productos as p WHERE p.codigo="+codigo;
    }

    public static String modificarProducto(Long codigo, Producto p){
        return "UPDATE productos SET nombre='"+p.getNombre()+"', detalle='"+p.getDetalle()+"', precio_venta="+p.getPrecio()+", alicuota= "+p.getAlicuota()+
                " WHERE codigo ='"+codigo+"'";
    }

    public static String deshabilitarProducto(Long codigo){
        return "UPDATE productos SET estado=false WHERE productos.codigo="+codigo;
    }

    public static String habilitarProducto(Long codigo){
        return "UPDATE productos SET estado=true WHERE productos.codigo="+codigo;
    }
    public static String obtenerProductoPorCodigo() {
        return "SELECT * FROM productos as p WHERE p.codigo= ?";
    }

    public static String obtenerId(Long codigo) {
        return "SELECT idProducto FROM productos as p WHERE p.codigo='"+codigo+"'";
    }

    public static String obtenerStockProducto(int idProducto) {
        return "SELECT SUM(s.stock_actual) FROM stock AS s WHERE id_producto = '"+idProducto+"'";
    }

    public static String obtenerProductoPorId() {
        return "SELECT * FROM productos as p WHERE p.idproducto= ?";
    }

    public static String obtenerStocks() {
        return "SELECT * FROM stock as s WHERE s.stock_actual != 0 AND s.id_producto= ? ORDER BY idstock ASC";
    }

    public static String obtenerMenorIdStock() {
        return "SELECT MIN(idstock) FROM stock WHERE stock.id_producto = ?";
    }

        public static String modificarStock(int idStock, int stockActual){
        return "UPDATE stock SET stock_actual='"+ stockActual +"' WHERE idstock ='"+idStock+"'";
    }
}
