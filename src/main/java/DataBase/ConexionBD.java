package DataBase;
import java.sql.*;
public class ConexionBD {
    private static String user = "postgres";
    private static String password = "unnoba";
    private static Connection connection;

    public ConexionBD(){}

    public static Connection conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SistemaContable",user,password);
            if(connection != null){ System.out.println("The connection is succesfull"); }
            else{ System.out.println("The connection is not succesfull"); }
        }
        catch (Exception e){ System.out.println(e.getMessage()); }
        return connection;
    }
}
