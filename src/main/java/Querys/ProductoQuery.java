package Querys;

import Model.Producto;

public class ProductoQuery {

    public static String insertarProducto(){
        return "INSERT INTO productos (codigo,nombre,detalle,precio_venta,estado,alicuota,stock) VALUES (?,?,?,?,?,?,?)";
    }

    public static String listarProductosHabilitados(){
        return "SELECT * FROM productos as p ORDER BY p.codigo ASC";
    }


    public static String obtenerAlicuota(Long codigo){
        return "SELECT alicuota FROM productos as p WHERE p.codigo="+codigo;
    }

    public static String existeProducto(Long codigo){
        return "SELECT * FROM productos as p WHERE p.codigo="+codigo;
    }

    public static String modificarProducto(Long codigo, Producto p){
        return "UPDATE productos SET nombre='"+p.getNombre()+"', detalle='"+p.getDetalle()+"', precio_venta="+p.getPrecio()+", stock= "+p.getStock()+"" +", alicuota= "+p.getAlicuota()+
                " WHERE codigo ='"+codigo+"'";
    }

    public static String deshabilitarProducto(Long codigo){
        return "UPDATE productos SET estado=false WHERE productos.codigo="+codigo;
    }

    public static String habilitarProducto(Long codigo){
        return "UPDATE productos SET estado=true WHERE productos.codigo="+codigo;
    }


}
