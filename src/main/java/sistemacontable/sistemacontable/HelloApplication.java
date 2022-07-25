package sistemacontable.sistemacontable;
import DataBase.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/login-user.fxml"));
            Parent root = fxmlLoader.load();
            getClass().getClassLoader().getResource("/Controller/LoginController");
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
                System.out.println(e);
        }
    }

    public static void main(String[] args) {
        /* Database connection test */
        ConexionBD conexionBD = new ConexionBD();
        conexionBD.conexion();
        launch();
    }
}