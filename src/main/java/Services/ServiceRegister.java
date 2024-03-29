package Services;
import DataBase.ConexionBD;
import Model.Alerta;
import Model.User;
import Model.Usuario_Rol;
import Querys.UserQuery;;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ServiceRegister extends Service {

    public boolean existeUser(String email) {
        try {
            setConexion(ConexionBD.conexion());
            String userEmail = UserQuery.existeUser(email);
            setPs(getConexion().prepareStatement(userEmail));
            setTupla(getPs().executeQuery());
            return getTupla().next();
        } catch (SQLException exception) { System.out.println(exception.getMessage()); }
        return false;
    }
    public boolean insertarUsuario(User usuario) { // 1|nombre  2|apellido  3|email  4|constrasena
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.insertarUsuario()));
            agregarAtributosUsuario(getPs(), usuario);
            getPs().executeUpdate();
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

    public void asginarRol(Usuario_Rol usuario_rol){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.asignarRol()));
            agregarAtributosUsuario_Rol(getPs(), usuario_rol);
            getPs().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public void agregarAtributosUsuario_Rol(PreparedStatement ps, Usuario_Rol usuario_rol) throws SQLException {
        ps.setInt(1,usuario_rol.getUsuario());
        ps.setInt(2,usuario_rol.getRol());
    }

}
