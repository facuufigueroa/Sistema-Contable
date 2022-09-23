package Services;

import DataBase.ConexionBD;
import Model.Cuenta;
import Querys.CuentaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ServicePDC {

    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    CuentaQuery cuentaQuery = new CuentaQuery();

    public ArrayList<Cuenta> listCuentasHabilitadas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.listarCuentasHabilitadas()));
            setResultSet(preparedStatement.executeQuery());
            while(resultSet.next()){
                Cuenta cuenta = new Cuenta();
                cuenta.codigo= getResultSet().getString(2);
                cuenta.nombre=getResultSet().getString(3);
                cuenta.recibe_saldo=cuentasRecibeSaldoSiNo(getResultSet().getString(4));
                cuenta.tipo=getResultSet().getString(5);
                cuentas.add(cuenta);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
        return cuentas;
    }

    /*Metodo que convierte los valores 0 y 1 del campo recibe_saldo en Si y No*/
   public String cuentasRecibeSaldoSiNo(String recibe_saldo){
        String recibe;
        if(Objects.equals(recibe_saldo, "1")){
            recibe="Si";
        }
        else{
            recibe="No";
        }
        return recibe;
    }

    public ArrayList<Cuenta> listCuentasDeshabilitadas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.listarCuentasDeshabilitadas()));
            setResultSet(preparedStatement.executeQuery());
            while(resultSet.next()){
                Cuenta cuenta = new Cuenta();
                cuenta.codigo= getResultSet().getString(2);
                cuenta.nombre=getResultSet().getString(3);
                cuenta.recibe_saldo=cuentasRecibeSaldoSiNo(getResultSet().getString(4));
                cuenta.tipo=getResultSet().getString(5);
                cuentas.add(cuenta);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
        return cuentas;
    }


    public void deshabilitarCuenta(String codigo){
       try{
           setConnection(ConexionBD.conexion());
           setPreparedStatement(getConnection().prepareStatement(cuentaQuery.deshabilitarCuenta(codigo)));
           getPreparedStatement().executeUpdate();
       }catch (Exception e){
           System.out.println(e);
       }
    }

    public void habilitarCuenta(String codigo){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.habilitarCuenta(codigo)));
            getPreparedStatement().executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void insertarCuenta(Cuenta cuenta) throws SQLException {
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.insertarCuenta()));
            getPreparedStatement().setString(1, cuenta.codigo);
            getPreparedStatement().setString(2, cuenta.nombre);
            getPreparedStatement().setString(3,cuenta.recibe_saldo);
            getPreparedStatement().setString(4,cuenta.tipo);
            getPreparedStatement().setBoolean(5, cuenta.estado);

            // Ejecuto la consulta
            getPreparedStatement().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public boolean existeCuenta(String codigo){
        boolean codigo_existe = false;
        try {
            setConnection(ConexionBD.conexion());
            String codigoCuenta = cuentaQuery.existeCodigoCuenta(codigo);
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

    public boolean recibeSaldo(String codigo){
        boolean recibe_saldo = false;
        try {
            setConnection(ConexionBD.conexion());
            String recibeSaldo = cuentaQuery.recibeSaldo(codigo);
            setPreparedStatement(getConnection().prepareStatement(recibeSaldo));
            setResultSet(preparedStatement.executeQuery());
            if (resultSet.next()) {
                recibe_saldo = true;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return recibe_saldo;
    }

    public ArrayList<String> traerNombreCuentas(){
        ArrayList<String> nombreCuentas = new ArrayList<>();
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(cuentaQuery.traerNombresCuentas()));
            setResultSet(preparedStatement.executeQuery());
            while(resultSet.next()){
                String nombreCuenta = getResultSet().getString(1);
                nombreCuentas.add(nombreCuenta);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
        return nombreCuentas;
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
