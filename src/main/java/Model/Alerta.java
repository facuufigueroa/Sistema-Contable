package Model;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class Alerta {
    public static boolean alertaEmailYaExiste() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡El email ya existe!\nPor favor, ingrese otro email.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaSeleccioneRol() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor! Seleccione un rol para el nuevo usuario");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaDatosInvalidos() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor!\nIngrese los datos correctamente");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaEmailInexistente() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡El email no existe!\nPor favor, ingrese un email correcto.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaCamposIncompletos() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los campos!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaContrasenaInvalida() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡La contraseña es incorrecta!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaLogueadoCorrectamente() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Usuario logueado correctamente!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }
    public static boolean alertaRegistradoCorrectamente() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Usuario registrado correctamente!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

    public static boolean recibeSaldo(String nombreCuenta) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("La cuenta "+"'"+ nombreCuenta +"'" +" no recibe saldo, no se puede deshabilitar. Solo es posible deshabiltiar cuentas que reciben saldo");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

    public static boolean alertaCuentaRegistradaCorretamente() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Cuenta registrada correctamente!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

    public static boolean alertaNoSeleccionoCuenta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡No seleccionó ninguna cuenta a deshabilitar !");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }


}
