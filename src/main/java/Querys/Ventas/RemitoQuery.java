package Querys.Ventas;

public class RemitoQuery {

    public static String insertarRemito(){
        return "INSERT INTO remitos (fecha,cantidad,descripcion,idventa,numero) VALUES (?,?,?,?,?)";
    }

    public static  String ultimoRemito(){
        return "select MAX(idremito) as idremito from remitos";
    }

}
