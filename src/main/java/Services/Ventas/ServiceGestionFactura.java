package Services.Ventas;
import DataBase.ConexionBD;
import Model.Ventas.TablaGestionFactura;
import Querys.Ventas.GestionFacturaQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceGestionFactura {
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    public ServiceGestionFactura(){}

    public void cerrarConexion(){
        setConnection(null);
        setPs(null);
        setResultSet(null);
    }

    public ArrayList<TablaGestionFactura> listarFacturas(){
        try{
            cerrarConexion();
            ArrayList<TablaGestionFactura> listado = new ArrayList<>();

            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(GestionFacturaQuery.listarFacturas()));
            setResultSet(getPs().executeQuery());
            while (getResultSet().next()){
                TablaGestionFactura factura = null;
                listado.add(factura);
            }
            return listado;
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return null;
    }

    public Connection getConnection() { return connection; }
    public void setConnection(Connection connection) { this.connection = connection; }
    public PreparedStatement getPs() { return ps; }
    public void setPs(PreparedStatement ps) { this.ps = ps; }
    public ResultSet getResultSet() { return resultSet; }
    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }
}
