package DataBase;
import java.sql.*;
public class ConexionBD {
    final String user = "postgres";
    final String password = "unnoba";

    public void conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SistemaContable",user,password);
            if(connection != null){ System.out.println("The connection is succesfull"); }
            else{ System.out.println("The connection is not succesfull"); }
        }
        catch (Exception e){ System.out.println(e); }
    }
}
