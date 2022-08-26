package DataBase;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

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
        catch (Exception e){
            System.out.println(e.getMessage());
            notificarError();
        }
        return connection;
    }
    private static void notificarError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al intentar conectarse a la base de datos.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
}
