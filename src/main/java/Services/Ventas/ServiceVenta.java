package Services.Ventas;

import DataBase.ConexionBD;
import Querys.ProductoQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceVenta {
    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ProductoQuery productoQuery;


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
