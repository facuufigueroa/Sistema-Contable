package Services;

import DataBase.ConexionBD;
import Model.CambiarFecha;
import Model.TablaMayor;
import Querys.CuentaQuery;
import Querys.LibroMayorQuery;
import Querys.QueryAsiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServiceLibroMayor extends Service{
    public ServiceLibroMayor(){}

    public ArrayList<String> traerNombreCuentas(){
        ArrayList<String> nombreCuentas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(CuentaQuery.traerNombresCuentas());
            rs = ps.executeQuery();
            while(rs.next()){
                String nombreCuenta = rs.getString(1);
                nombreCuentas.add(nombreCuenta);
            }
        }catch (Exception exception){ System.out.println(exception); }
        return nombreCuentas;
    }

    public int obtenerIdCuenta(String nombreCuenta){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(QueryAsiento.obtenerIdCuenta(nombreCuenta.toUpperCase()));
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        finally { vaciarConexion();}
        return -1;
    }

    public ArrayList<Integer> obtenerIdAsiento(int idCuenta, LocalDate desde, LocalDate hasta){
        ArrayList<Integer> lista = new ArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerIdAsientoPorFecha());
            ps.setInt(1, idCuenta);
            ps.setDate(2, CambiarFecha.localDateToDate(desde));
            ps.setDate(3, CambiarFecha.localDateToDate(hasta));
            rs = ps.executeQuery();
            while (rs.next()){
               lista.add(rs.getInt(1));
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return lista;
    }

    public String obtenerDetallePorId(int idAsiento){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerDetallePorId());
            ps.setInt(1, idAsiento);
            rs = ps.executeQuery();
            if (rs.next()){ return rs.getString(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return "";
    }

    public double obtenerDebe(int idAsiento, int idCuenta){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerDebePorId());
            ps.setInt(1, idAsiento);
            ps.setInt(2,idCuenta);
            rs = ps.executeQuery();
            if (rs.next()){ return rs.getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    public String conversionDebeHaber(Double debeHaber){
        if (debeHaber == 0.0) {
            return "";
        }
        return String.valueOf(debeHaber);
    }
    public double obtenerHaber(int idAsiento, int idCuenta){
        try{
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerHaberPorId());
            ps.setInt(1, idAsiento);
            ps.setInt(2,idCuenta);
            rs = ps.executeQuery();
            if (rs.next()){ return rs.getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    public double obtenerSaldo(int idAsiento, int idCuenta){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerSaldoPorId());
            ps.setInt(1, idAsiento);
            ps.setInt(2,idCuenta);
            rs = ps.executeQuery();
            if (rs.next()){ return rs.getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public ArrayList<TablaMayor> obtenerTablaMayor(int idCuenta, LocalDate desde, LocalDate hasta){
        ArrayList<TablaMayor> lista = new ArrayList<>();
        for (Integer valor : obtenerIdAsiento(idCuenta, desde, hasta)){
            String detalle = obtenerDetallePorId(valor);
            String debe = conversionDebeHaber(obtenerDebe(valor, idCuenta));
            String haber = conversionDebeHaber(obtenerHaber(valor, idCuenta));

            double saldo = obtenerSaldo(valor, idCuenta);
            TablaMayor tablaMayor = new TablaMayor(valor, detalle, debe, haber, saldo);
            lista.add(tablaMayor);
        }
        return lista;
    }
}
