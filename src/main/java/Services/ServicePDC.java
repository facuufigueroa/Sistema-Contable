package Services;

import DataBase.ConexionBD;
import Model.Cuenta;
import Querys.CuentaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ServicePDC {

    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    CuentaQuery cuentaQuery = new CuentaQuery();

    public ArrayList<Cuenta> listCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.listarCuentas()));
            setResultSet(preparedStatement.executeQuery());
            while(resultSet.next()){
                Cuenta cuenta = new Cuenta();
                cuenta.codigo= getResultSet().getString(2);
                cuenta.nombre=getResultSet().getString(3);
                cuenta.recibe_salgo=getResultSet().getString(4);
                cuenta.tipo=getResultSet().getString(5);
                cuentas.add(cuenta);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
        return cuentas;
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
