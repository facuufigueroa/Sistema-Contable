package Model.Ventas;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class AlertaVenta {
    public static void datosPersonaIncompleta(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Datos Incompletos\n¡Por favor! Complete todos los datos");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void seleccioneTipoPersona(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor!\nElija un tipo de persona");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
}
