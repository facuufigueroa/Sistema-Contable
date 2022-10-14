package Services;

import DataBase.ConexionBD;
import Querys.CalcularSaldoQuery;

public class ServiceCalcularSaldoCuenta extends Service{
    public double obtenerSaldoCuenta(int idCuenta){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(CalcularSaldoQuery.obtenerSaldoCuenta()));
            getPs().setInt(1, idCuenta);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getDouble(1); }
        }catch (Exception e){ System.out.println(e.getMessage()); }
        finally { vaciarConexion(); }
        return 0;
    }
    public void actualizarSaldoCuenta(int idCuenta, double monto){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(CalcularSaldoQuery.sumarRestarSaldoCuenta()));
            getPs().setDouble(1, monto);
            getPs().setInt(2, idCuenta);
            getPs().executeUpdate();
        }catch (Exception e){ System.out.println(e.getMessage()); }
        finally { vaciarConexion(); }
    }
}
