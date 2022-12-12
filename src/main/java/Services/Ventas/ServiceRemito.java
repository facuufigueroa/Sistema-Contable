package Services.Ventas;

import DataBase.ConexionBD;
import Model.Ventas.Remito;
import Querys.Ventas.RemitoQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceRemito {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    private RemitoQuery remitoQuery;

    public void insertarRemito(Remito remito) throws SQLException {
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(remitoQuery.insertarRemito()));
            getPs().setDate(1, remito.getFecha());
            getPs().setInt(2,remito.getCantidad());
            getPs().setString(3,remito.getDescripcion());
            getPs().setInt(4, remito.getId_venta());
            getPs().setString(5, remito.getNumero());
            getPs().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public int ultimoRemito() throws SQLException {
        int id_ultimoRemito=0;
        setConnection(ConexionBD.conexion());
        setPs(getConnection().prepareStatement(remitoQuery.ultimoRemito()));
        setResultSet(ps.executeQuery());
        if (resultSet.next()) {
            id_ultimoRemito = getResultSet().getInt(1);
        }
        return id_ultimoRemito;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
