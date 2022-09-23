package sistemacontable.sistemacontable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PruebaAsiento extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/registro-asientos.fxml"));
            Parent root = fxmlLoader.load();
            getClass().getClassLoader().getResource("/Controller/AsientoController");
            Scene scene = new Scene(root);
            stage.setTitle("FBF System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        }catch (Exception e){ System.out.println(e.getMessage()); }
    }
}
