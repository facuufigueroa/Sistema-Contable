package Services;

import DataBase.ConexionBD;
import Model.Asiento;
import Model.AsientoCuenta;
import Model.CambiarFecha;
import Querys.QueryAsiento;
import Querys.RolesQuery;


import java.sql.SQLException;

public class ServiceAsiento extends Service {



    public int insertarAsiento(Asiento asiento) { //1|fecha 2|detalle 3|usuario
        int codigoAsiento=0;
        setConexion(null);
        setPs(null);
        setTupla(null);
        try {
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(QueryAsiento.insertarAsiento()));
            getPs().setDate(1, CambiarFecha.localDateToDate(asiento.getFecha()));
            getPs().setString(2, asiento.getDetalle());
            getPs().setInt(3, asiento.getUsuario());
            getPs().executeUpdate();

            return codigoAsiento;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return codigoAsiento;
    }

    public void insertarAsientoCuenta(AsientoCuenta asiento_cuenta) {
        setConexion(null);
        setPs(null);
        setTupla(null);
        try {
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(QueryAsiento.insertarCuentaAsiento()));
            getPs().setDouble(1, asiento_cuenta.getDebe());
            getPs().setDouble(2, asiento_cuenta.getHaber());
            getPs().setDouble(3, asiento_cuenta.getSaldo());
            getPs().setInt(4, asiento_cuenta.getAsiento());
            getPs().setInt(5, asiento_cuenta.getCuenta());
            getPs().executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public int obtenerIdCuenta(String nombreCuenta){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(QueryAsiento.obtenerIdCuenta(nombreCuenta)));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return -1;
    }

    public int obtenerIdAsiento(){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(QueryAsiento.obtenerIdAsiento()));
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return -1;
    }

    public String obtenerNombreCuenta(int idCuenta){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(QueryAsiento.obtenerNombreCuenta()));
            getPreparedStatement().setInt(1,idCuenta);
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }

    public String obtenerTipoDeCuenta(String nombreCuenta){
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(QueryAsiento.obtenerTipoCuenta()));
            getPreparedStatement().setString(1,nombreCuenta);
            setResultSet(getPreparedStatement().executeQuery());
            if(getResultSet().next()){
                return getResultSet().getString(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }




}
