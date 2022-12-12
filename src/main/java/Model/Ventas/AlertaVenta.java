package Model.Ventas;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class AlertaVenta {
    public static void facturaCobradaCorrectamente(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("La operación se realizo correctamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void errorAlCobrarFactura(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Ocurrio un error al cobrar la factura.\n¡Por favor! Intente nuevamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void noHayFacturas(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("La tabla no contiene facturas\nEs posible que no haya registros de facturas.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void seleccioneFactura(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("No selecciono ninguna factura\n¡Por favor! elija una factura de la tabla");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void dniExistente(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El DNI ya existe\n¡Por favor! Ingrese un dni valido");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void cuitExistente(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El CUIT ya existe\n¡Por favor! Ingrese un cuit valido");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void dniIncorrecto(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Dni incorrecto\n¡Por favor! Ingrese un dni valido");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
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
        alert.setContentText("Cliente registrado exitosamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteModificadoCorrectamente(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Cliente modificado exitosamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteNoModificado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al modificar el cliente.\n¡Por favor! Intentelo nuevamente");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteNoRegistrado(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al registrar el cliente.\n¡Por favor! Intentelo nuevamente");
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

    public static void clienteDeshabilitadoCorrectamente(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Cliente deshabilitado exitosamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteHabilitadoCorrectamente(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Cliente habilitado exitosamente.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteDeshabilitado(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El cliente ya se encuentra deshabilitado.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void clienteHabilitado(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El cliente ya se encuentra habilitado.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
}
