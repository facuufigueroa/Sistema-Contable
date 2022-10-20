package Services;
import DataBase.ConexionBD;
import Model.Alerta;
import Model.User;
import Querys.QueryAsiento;
import Querys.RolesQuery;
import Querys.UserQuery;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet tupla; //Es el conjunto de datos obtenidos desde la BD

    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public Service(){}


    public Connection getConexion() { return conexion; }
    public void setConexion(Connection conexion) { this.conexion = conexion; }
    public PreparedStatement getPs() { return ps; }
    public void setPs(PreparedStatement ps) { this.ps = ps; }
    public ResultSet getTupla() { return tupla; }
    public void setTupla(ResultSet tupla) { this.tupla = tupla; }

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

    public int obtenerIdUser(String email){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(RolesQuery.obtenerIdUser()));
            getPs().setString(1, email);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getInt(1); }
        }catch (SQLException e){ System.out.println("Id usuario: " + e.getMessage()); }
        finally { vaciarConexion(); }
        return -1;
    }
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
                        getTupla().getInt("idusuario"),
                        getTupla().getString("nombre"),
                        getTupla().getString("apellido"),
                        getTupla().getString("email"),
                        getTupla().getString("contraseña")
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

    public String obtenerRolPorEmail(String email){
        String nombreRol="";
        try{
            setConnection(ConexionBD.conexion());
            setPreparedStatement(getConnection().prepareStatement(RolesQuery.obtenerRolPorEmail(email)));
            setResultSet(preparedStatement.executeQuery());
            if(resultSet.next()){
               nombreRol=getResultSet().getString(1);
            }

        }catch (Exception exception){
            System.out.println(exception);
        }
        finally { vaciarConexion(); }
        return nombreRol;
    }
    public int obtenerIdRolNombreRol(String nombre){
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.obtenerIdRolNombreRol()));
            getPs().setString(1, nombre);
            setTupla(getPs().executeQuery());
            if (getTupla().next()){ return getTupla().getInt(1); }
        }catch (SQLException e){ System.out.println("Id usuario: " + e.getMessage()); }
        finally { vaciarConexion(); }
        return -1;
    }

    public ArrayList<User> obtenerUsuarios(){
        ArrayList<User> users = new ArrayList<>();
        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.obtenerUsuarios()));
            setTupla(getPs().executeQuery());
            while (getTupla().next()){
                User user = new User(
                                    getTupla().getString("nombre"),
                                    getTupla().getString("apellido"),
                                    getTupla().getString("email"),
                                    getTupla().getString("rol"),
                                    getTupla().getString("contraseña")
                );
                users.add(user);
            }
        }catch (SQLException e){ System.out.println(e.getMessage()); }
        return users;
    }

    public void insertarUsuarioRol(String email, String rol){
        int obtenerIdUser = obtenerIdUser(email);
        int obtenerIdRol = obtenerIdRolNombreRol(rol);

        try{
            setConexion(ConexionBD.conexion());
            setPs(getConexion().prepareStatement(UserQuery.asignarRol()));
            getPs().setInt(1, obtenerIdUser);
            getPs().setInt(2, obtenerIdRol);
            getPs().executeUpdate();
            Alerta.alertaRegistradoCorrectamente();
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
            notificarError();
        }
        finally { vaciarConexion(); }
    }

    public void vaciarConexion(){
        setPs(null);
        setConnection(null);
        setTupla(null);
        setConexionBD(null);
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
}
