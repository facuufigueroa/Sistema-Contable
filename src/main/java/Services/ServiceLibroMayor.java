package Services;

import DataBase.ConexionBD;
import Model.TablaMayor;
import Querys.CuentaQuery;
import Querys.LibroMayorQuery;
import Querys.QueryAsiento;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceLibroMayor extends Service{
    public ServiceLibroMayor(){}

    public ArrayList<String> traerNombreCuentas(){
        ArrayList<String> nombreCuentas = new ArrayList<>();
        vaciarConexion();
        try{
            setConnection(ConexionBD.conexion());
            setPs(getConnection().prepareStatement(CuentaQuery.traerNombresCuentas()));
            setTupla(getPs().executeQuery());
            while(getTupla().next()){
                String nombreCuenta = getTupla().getString(1);
                nombreCuentas.add(nombreCuenta);
            }
        }catch (Exception exception){ System.out.println(exception); }
        return nombreCuentas;
    }

    //id cuenta, detalles, debe, haber saldo

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
        finally { vaciarConexion();}
        return -1;
    }

    public ArrayList<Integer> obtenerIdAsiento(int idCuenta){
        ArrayList<Integer> lista = new ArrayList();
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(LibroMayorQuery.obtenerIdAsiento()));
            getPs().setInt(1, idCuenta);
            setTupla(getPs().executeQuery());
            while (getTupla().next()){
               lista.add(getTupla().getInt(1));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        finally { vaciarConexion();}
        return lista;
    }

    public String obtenerDetallePorId(int idAsiento){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(LibroMayorQuery.obtenerDetallePorId()));
            getPs().setInt(1, idAsiento);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getString(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        finally { vaciarConexion(); }
        return "";
    }

    //ArrayList asientos
    // por cada id agregarlo a un map
    public HashMap<Integer, String> obtenerDescripcionAsiento(int idCuenta){
        HashMap<Integer, String> map = new HashMap<>();
        ArrayList<Integer> lista = obtenerIdAsiento(idCuenta);
        for (Integer valor : lista) {
            String detalle = obtenerDetallePorId(valor);
            map.put(valor, detalle);
        }
        return map;
    }

    public double obtenerDebe(int idAsiento){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(LibroMayorQuery.obtenerDebePorId()));
            getPs().setInt(1, idAsiento);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        finally { vaciarConexion(); }
        return 0;
    }
    public double obtenerHaber(int idAsiento){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(LibroMayorQuery.obtenerHaberPorId()));
            getPs().setInt(1, idAsiento);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        finally { vaciarConexion(); }
        return 0;
    }
    public double obtenerSaldo(int idAsiento){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(LibroMayorQuery.obtenerSaldoPorId()));
            getPs().setInt(1, idAsiento);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        finally { vaciarConexion(); }
        return 0;
    }

    public ArrayList<TablaMayor> obtenerTablaMayor(int idCuenta){
        int idAsiento = 0;
        ArrayList<TablaMayor> lista = new ArrayList<>();
        HashMap<Integer, String> map = obtenerDescripcionAsiento(idCuenta);
        for (Integer valor : obtenerIdAsiento(idCuenta)){
            //TablaMayor tablaMayor = new TablaMayor(valor, obtenerDetallePorId(valor), ,);
        }
        return lista;
    }
}
