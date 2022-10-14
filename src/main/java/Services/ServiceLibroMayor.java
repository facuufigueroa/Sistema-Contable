package Services;

import DataBase.ConexionBD;
import Model.CambiarFecha;
import Model.Cuenta;
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
import java.util.HashMap;

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

    //id cuenta, detalles, debe, haber saldo

    public int obtenerIdCuenta(String nombreCuenta){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(QueryAsiento.obtenerIdCuenta(nombreCuenta));
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

    //ArrayList asientos
    // por cada id agregarlo a un map
    /*
    public HashMap<Integer, String> obtenerDescripcionAsiento(int idCuenta){
        HashMap<Integer, String> map = new HashMap<>();
        ArrayList<Integer> lista = obtenerIdAsiento(idCuenta);
        for (Integer valor : lista) {
            String detalle = obtenerDetallePorId(valor);
            map.put(valor, detalle);
        }
        return map;
    }


     */


    public double obtenerDebe(int idAsiento){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerDebePorId());
            ps.setInt(1, idAsiento);
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
    public double obtenerHaber(int idAsiento){
        try{
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerHaberPorId());
            ps.setInt(1, idAsiento);
            rs = ps.executeQuery();
            if (rs.next()){ return rs.getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    public double obtenerSaldo(int idAsiento){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = ConexionBD.conexion();
            ps = connection.prepareStatement(LibroMayorQuery.obtenerSaldoPorId());
            ps.setInt(1, idAsiento);
            rs = ps.executeQuery();
            if (rs.next()){ return rs.getDouble(1); }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    /*public ArrayList<TablaMayor> obtenerAsientosPorFecha(int idAsiento, LocalDate desde, LocalDate hasta){
        ArrayList<TablaMayor> lista = new ArrayList<>();
        try {
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(LibroMayorQuery.obtenerAsientosPorFecha()));
            getPs().setInt(1, idAsiento);
            getPs().setDate(2, CambiarFecha.localDateToDate(desde));
            getPs().setDate(3, CambiarFecha.localDateToDate(hasta));
            setResultSet(getPs().executeQuery());
            while (getResultSet().next()){ // idasiento , detalle, fecha
                TablaMayor asiento = new TablaMayor(getResultSet().getInt(1), getResultSet().getString(2));
                lista.add(asiento);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
*/
    public ArrayList<TablaMayor> obtenerTablaMayor(int idCuenta, LocalDate desde, LocalDate hasta){
       // int idAsiento = 0;
        //double saldoAux = 0;
        ArrayList<TablaMayor> lista = new ArrayList<>();
        //HashMap<Integer, String> map = obtenerDescripcionAsiento(idCuenta);
        for (Integer valor : obtenerIdAsiento(idCuenta, desde, hasta)){
            String detalle = obtenerDetallePorId(valor);
            String debe = conversionDebeHaber(obtenerDebe(valor));
            String haber = conversionDebeHaber(obtenerHaber(valor));

            //String nombreCuenta = obtenerNombreCuenta(idCuenta);
            //String tipoCuenta = obtenerTipoDeCuenta(nombreCuenta);
            //String debeHaber = obtenerSiEsDebeHaber(String.valueOf(debe));
            double saldo = obtenerSaldo(valor);
            //saldoAux = saldo;
            TablaMayor tablaMayor = new TablaMayor(valor, detalle, debe, haber, saldo);
            lista.add(tablaMayor);
        }
        lista.forEach(cuenta -> System.out.println(cuenta));
        return lista;
    }
  /*public String obtenerSiEsDebeHaber(String debe){
        return (debe.equals("")) ? "Haber" : "Debe";
    }

      public double verificarTipoCuenta(String tipo, String debeHaber, double saldoActual, double saldoFinal){
        if (tipo.equals("Ac") &&  debeHaber.equals("Debe")){
            return saldoActual+saldoFinal;

        } else if (tipo.equals("Ac") && debeHaber.equals("Haber")) {
            return saldoActual-saldoFinal;
        }
        else if ((tipo.equals("Pa") || tipo.equals("Pm")) &&  debeHaber.equals("Debe")){
            return saldoActual-saldoFinal;
        }
        else if ((tipo.equals("Pa") || tipo.equals("Pm")) &&  debeHaber.equals("Haber")){
            return saldoActual+saldoFinal;
        }
        else if ((tipo.equals("R+") || tipo.equals("R-")) &&  debeHaber.equals("Debe")){
            return saldoActual+saldoFinal;
        }
        return 0;
    }
*/
}
