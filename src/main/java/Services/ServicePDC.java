package Services;

import DataBase.ConexionBD;
import Model.Cuenta;
import Querys.CuentaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServicePDC {

    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    CuentaQuery cuentaQuery = new CuentaQuery();

    public Cuenta listCuentas() {
        Cuenta cuenta = new Cuenta();
        try{
            setConnection(ConexionBD.conexion());

            // Consulta a la tabla
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.listarCuentas()));
            // Ejecuto la consulta
            getPreparedStatement().executeUpdate();

            if(getResultSet().next()){
                cuenta.codigo= getResultSet().getString(2);
                cuenta.nombre=getResultSet().getString(3);
                cuenta.recibe_salgo=getResultSet().getString(4);
                cuenta.tipo=getResultSet().getString(5);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
        return cuenta;
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

    public CuentaQuery getCuentaQuery() {
        return cuentaQuery;
    }

    public void setCuentaQuery(CuentaQuery cuentaQuery) {
        this.cuentaQuery = cuentaQuery;
    }
}
