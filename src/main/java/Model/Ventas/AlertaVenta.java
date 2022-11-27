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
    public static void clienteRegistrado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Usuario registrado exitosamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteNoRegistrado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al registrar el usuario.\n¡Por favor! Intentelo nuevamente");
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
    public static void seleccioneCliente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("No selecciono ningún cliente de la tabla.\n¡Por favor! Elija un cliente para modificar");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
}
