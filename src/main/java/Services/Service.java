package Services;
import DataBase.ConexionBD;
import Model.User;
import Querys.UserQuery;
import com.administrativos.sistema.utilidades.Alerta;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

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
    public boolean insertarUsuario(User usuario) throws SQLException{ // 1|nombre  2|apellido  3|email  4|constrasena
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.insertarUsuario()));
            agregarAtributosUsuario(getPs(), usuario);
            getPs().executeUpdate();
            Alerta.alertaRegistradoCorrectamente();
            return true;
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
            notificarError();
        }
        return false;
    }
    private void agregarAtributosUsuario(PreparedStatement ps, User usuario) throws SQLException{
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getApellido());
        ps.setString(3, usuario.getEmail());
        ps.setString(4, usuario.getContrasena());
    }
    private static void notificarError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al registrar el usuario");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

}
