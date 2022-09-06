package Services;
import DataBase.ConexionBD;
import Model.User;
import Querys.UserQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet tupla; //Es el conjunto de datos obtenidos desde la BD

    public Connection getConexion() { return conexion; }
    public void setConexion(Connection conexion) { this.conexion = conexion; }
    public PreparedStatement getPs() { return ps; }
    public void setPs(PreparedStatement ps) { this.ps = ps; }
    public ResultSet getTupla() { return tupla; }
    public void setTupla(ResultSet tupla) { this.tupla = tupla; }


    public boolean existeObjeto(String tabla, String atributo, Object objeto){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.existeObjeto(tabla, atributo)));
            getPs().setObject(1, objeto);
            setTupla(getPs().executeQuery());
            return getTupla().next();
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        return false;
    }
    public User obtenerUsuarioPorEmail(String email){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.obtenerUsuarioPorEmail()));
            getPs().setString(1, email);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){
                return new User(
                        getTupla().getString("nombre"),
                        getTupla().getString("apellido"),
                        getTupla().getString("email"),
                        getTupla().getString("contrasena")
                );
            }
            return null;
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        return null;
    }
}
