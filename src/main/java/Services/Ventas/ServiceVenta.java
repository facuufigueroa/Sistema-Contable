package Services.Ventas;

import DataBase.ConexionBD;
import Model.Producto;
import Model.Ventas.TablaVistaVenta;
import Model.Ventas.Venta;
import Querys.ProductoQuery;
import Querys.VentaQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceVenta {

    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private VentaQuery ventaQuery;

    public void insertarVenta(Venta venta) throws SQLException {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(ventaQuery.insertarVenta()));
            getPreparedStatement().setDouble(1, venta.getTotalBruto());
            getPreparedStatement().setDouble(2, venta.getTotalNeto());
            getPreparedStatement().setDouble(3,venta.getTotales());
            getPreparedStatement().setInt(4,venta.getFormaPago());
            getPreparedStatement().setInt(5, venta.getIdCliente());
            getPreparedStatement().setInt(6,venta.getIdUsuario());
            getPreparedStatement().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public void insertarVenta_producto(TablaVistaVenta venta) throws SQLException {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(ventaQuery.insertarVenta_producto()));
            getPreparedStatement().setInt(1, venta.getIdVenta());
            getPreparedStatement().setInt(2, venta.getIdProducto());
            getPreparedStatement().setInt(3,venta.getCantidad());
            getPreparedStatement().setDouble(4,venta.getPrecioUnitario());
            getPreparedStatement().setDouble(5,venta.getPrecioTotal());
            getPreparedStatement().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }
    public String obtenerFormaPago(int formaPago) {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(VentaQuery.obtenerFormaPago(formaPago)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return "Error";
    }

    public String obtenerNombreCuenta(int formaPago) {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(VentaQuery.obtenerNombreCuenta(formaPago)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return "Error";
    }

    public int obtenerIdformaPago(String formaPago) {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(VentaQuery.obtenerIdFormaPago(formaPago)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return -1;
    }

    public int obtenerIdVenta() {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(VentaQuery.obtenerIdVenta()));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return -1;
    }

    public String obtenerCondicionIvaCliente(int idCliente){
        String ci="";
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(VentaQuery.obtenerCondicionIvaCliente(idCliente)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                ci=getResultSet().getString(1);
                return ci.trim();
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return "error";
    }
    public ConexionBD getConexionBD() { return conexionBD; }

    public void setConexionBD(ConexionBD conexionBD) { this.conexionBD = conexionBD; }

    public Connection getConnection() { return connection; }

    public void setConnection(Connection connection) { this.connection = connection; }

    public PreparedStatement getPreparedStatement() { return preparedStatement; }

    public void setPreparedStatement(PreparedStatement preparedStatement) { this.preparedStatement = preparedStatement; }

    public ResultSet getResultSet() { return resultSet; }

    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }

    public VentaQuery getVentaQuery() { return ventaQuery; }

    public void setVentaQuery(VentaQuery ventaQuery) { this.ventaQuery = ventaQuery; }
}
