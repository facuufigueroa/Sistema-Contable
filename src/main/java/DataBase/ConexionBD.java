package DataBase;
import java.sql.*;
public class ConexionBD {
    final static String user = "postgres";
    final static String password = "unnoba";
    private static Connection connection;

    public ConexionBD(){}

    public static Connection conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SistemaContable",user,password);
        }
        catch (Exception e){ System.out.println(e.getMessage()); }
        return connection;
    }
}
