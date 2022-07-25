package Services;
import DataBase.ConexionBD;
import Querys.UserQuery;
import Model.*;

import java.sql.*;

public class ServiceLogin {
    //Atributos
    private ConexionBD conexionBD;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    UserQuery userQuery=new UserQuery();

    //Constructor
    public ServiceLogin(){}

    //Getters y Setters
    public ConexionBD getConexionBD() { return conexionBD; }
    public void setConexionBD(ConexionBD conexionBD) { this.conexionBD = conexionBD; }
    public Connection getConnection() { return connection; }
    public void setConnection(Connection connection) { this.connection = connection; }
    public PreparedStatement getPreparedStatement() { return preparedStatement; }
    public void setPreparedStatement(PreparedStatement preparedStatement) { this.preparedStatement = preparedStatement; }
    public ResultSet getResultSet() { return resultSet; }
    public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }

    //Métodos
    /**
     * Inserta en la base de datos un usuario nuevo
     */
    public void insertUser(User user) throws SQLException { // 1|email, 2|password
        try{
            // Conexión a la base de datos
            setConnection(ConexionBD.conexion());

            // Consulta a la tabla
            // RETURN_GENERATED_KEYS Genera la clave iduser automaticamente.
            setPreparedStatement(getConnection().prepareStatement(UserQuery.insertarUsuario(), PreparedStatement.RETURN_GENERATED_KEYS));

            // Ingreso valores de user
            getPreparedStatement().setString(1, user.getEmail());
            getPreparedStatement().setString(2, user.getPassword());

            // Ejecuto la consulta
            getPreparedStatement().executeUpdate();
        }catch (SQLException exception){ System.out.println(exception.getMessage()); }
    }

    public boolean existeUser(String email) {
        boolean existeU = false;
        try {
            setConnection(ConexionBD.conexion());
            String userEmail = userQuery.existeUser(email);
            setPreparedStatement(getConnection().prepareStatement(userEmail));
            setResultSet(preparedStatement.executeQuery());
            if (resultSet.next()) {
                existeU = true;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return existeU;
    }

}
