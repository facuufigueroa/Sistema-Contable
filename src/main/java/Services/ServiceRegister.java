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

public class ServiceRegister {
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet tupla; //Es el conjunto de datos obtenidos desde la BD

    public Connection getConexion() { return conexion; }
    public void setConexion(Connection conexion) { this.conexion = conexion; }
    public PreparedStatement getSentencia() { return sentencia ;}
    public void setSentencia(PreparedStatement sentencia) { this.sentencia = sentencia; }
    public ResultSet getTupla() { return tupla; }
    public void setTupla(ResultSet tupla) { this.tupla = tupla; }


    public boolean existeUser(String email) {
        try {
            setConexion(ConexionBD.conexion());
            String userEmail = UserQuery.existeUser(email);
            setSentencia(getConexion().prepareStatement(userEmail));
            setTupla(getSentencia().executeQuery());
            return getTupla().next();
        } catch (SQLException exception) { System.out.println(exception.getMessage()); }
        return false;
    }
    public boolean insertarUsuario(User usuario) throws SQLException{ // 1|nombre  2|apellido  3|email  4|constrasena
        try{
            setConexion(ConexionBD.conexion());
            setSentencia(getConexion().prepareStatement(UserQuery.insertarUsuario()));
            agregarAtributosUsuario(getSentencia(), usuario);
            getSentencia().executeUpdate();
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
