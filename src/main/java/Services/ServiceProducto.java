package Services;

import DataBase.ConexionBD;
import Model.Cuenta;
import Model.Producto;
import Model.Ventas.Cliente;
import Querys.ProductoQuery;
import Querys.QueryAsiento;
import Querys.Ventas.ClienteQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceProducto {

    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private ProductoQuery productoQuery;

    public void insertarProducto(Producto producto) throws SQLException {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(productoQuery.insertarProducto()));
            getPreparedStatement().setLong(1, producto.getCodigo());
            getPreparedStatement().setString(2, producto.getNombre());
            getPreparedStatement().setString(3,producto.getDetalle());
            getPreparedStatement().setDouble(4,producto.getPrecio());
            getPreparedStatement().setBoolean(5,producto.isEstado());
            getPreparedStatement().setDouble(6, producto.getAlicuota());
            getPreparedStatement().setInt(7, producto.getStock());
            getPreparedStatement().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(productoQuery.listarProductos()));
            setResultSet(preparedStatement.executeQuery());
            while(resultSet.next()){
                Producto producto = new Producto();
                producto.setCodigo(getResultSet().getLong(2));
                producto.setNombre(getResultSet().getString(3));
                producto.setDetalle(getResultSet().getString(4));
                producto.setPrecio(getResultSet().getDouble(5));
                producto.setEstado(getResultSet().getBoolean(6));
                producto.setAlicuota(getResultSet().getDouble(7));
                producto.setStock(getResultSet().getInt(8));
                productos.add(producto);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return productos;
    }

    public ArrayList<Producto> listarProductosHabilitados() {
        ArrayList<Producto> productos = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(productoQuery.listarProductosHabilitados()));
            setResultSet(preparedStatement.executeQuery());
            while(resultSet.next()){
                Producto producto = new Producto();
                producto.setCodigo(getResultSet().getLong(2));
                producto.setNombre(getResultSet().getString(3));
                producto.setDetalle(getResultSet().getString(4));
                producto.setPrecio(getResultSet().getDouble(5));
                producto.setEstado(getResultSet().getBoolean(6));
                producto.setAlicuota(getResultSet().getDouble(7));
                producto.setStock(getResultSet().getInt(8));
                productos.add(producto);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return productos;
    }
    public boolean existeProducto(String codigo){
        boolean codigo_existe = false;
        try {
            setConnection(ConexionBD.conexion());
            String codigoCuenta = productoQuery.existeProducto(Long.valueOf(codigo));
            setPreparedStatement(getConnection().prepareStatement(codigoCuenta));
            setResultSet(preparedStatement.executeQuery());
            if (resultSet.next()) {
                codigo_existe = true;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return codigo_existe;
    }

    public String obtenerAlicuotaProducto(String codigo){
        try{
            Long codProducto = Long.valueOf(codigo);
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(ProductoQuery.obtenerAlicuota(codProducto)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return "Error en el servicio/query al obtener alicuota";
    }

    public int obtenerIdProducto(Long codigo) {
        try{
            Long codProducto = Long.valueOf(codigo);
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(ProductoQuery.obtenerId(codProducto)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return -1;
    }

    public void modificarProducto(String codigo, Producto producto){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(productoQuery.modificarProducto(Long.valueOf(codigo),producto)));
            getPreparedStatement().executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void deshabilitarProducto(String codigo){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(productoQuery.deshabilitarProducto(Long.valueOf(codigo))));
            getPreparedStatement().executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void habilitarProducto(String codigo){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(productoQuery.habilitarProducto(Long.valueOf(codigo))));
            getPreparedStatement().executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Producto obtenerProductoPorCodigo(Long codigoProducto){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(ProductoQuery.obtenerProductoPorCodigo()));
            getPreparedStatement().setLong(1, codigoProducto);
            setResultSet(getPreparedStatement().executeQuery());
            if (getResultSet().next()){
                return new Producto(
                        getResultSet().getLong("codigo")
                        ,getResultSet().getString("nombre")
                        ,getResultSet().getString("detalle")
                        ,getResultSet().getDouble("precio_venta")
                        ,getResultSet().getInt("id_stock")
                        ,getResultSet().getDouble("alicuota")
                        ,getResultSet().getBoolean("estado")
                );
            }
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return null;
    }

    public ConexionBD getConexionBD() {
        return conexionBD;
    }

    public void setConexionBD(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public ProductoQuery getProductoQuery() {
        return productoQuery;
    }

    public void setProductoQuery(ProductoQuery productoQuery) {
        this.productoQuery = productoQuery;
    }

}
