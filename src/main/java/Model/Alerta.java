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
    public static boolean alertaSaldo() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡La operación no puede realizarse, debido a que una cuenta puede quedar en negativo!\nPor favor, compruebe el saldo en el plan de cuentas.");
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

    public static boolean alertaEmailInexistente() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡El email no existe!\nPor favor, ingrese un email correcto.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaEmailInvalido() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡El email es invalido!\nPor favor, ingrese un email correcto.");
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
        alert.setContentText("La cuenta "+"'"+ nombreCuenta +"'" +" no recibe saldo, no se puede deshabilitar.\nSolo es posible deshabiltiar cuentas que reciben saldo.");
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

    public static boolean alertarAsientoRegistrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡ Asiento registrado con éxito !");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }
    public static Alert alertarCancelar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¿Está seguro de cancelar el asiento?");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return alert;
    }

    public static boolean alertarAsientoNoBalanceado() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al registrar asiento. \n" +
                "La resta del total debe y total haber no coinciden, los balances no son correctos. ¡Verifique nuevamente!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

    public static boolean alertarCampoDebeHaberVacio() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error, seleccione Debe o Haber");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }


    public static Alert alertaNuevoAsiento() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¿Desea registrar otro asiento?");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return alert;
    }


    public static boolean alertaCamposIncompletosOperacion() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los campos, verifique que: \n 1) Haya seleccionado la Cuenta " +
                "\n 2) Haya escrito un monto" +
                "\n 3) Haya seleccionado Debe o Haber!");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }
    public static boolean alertaCamposIncompletosRegistrarAsiento() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los campos, verifique que: \n 1) Haya escrito una descripcion " +
                "\n 2) Haya registrado un asiento");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }

    public static boolean alertaFechaIncompleta() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, elija una fecha desde y una fecha hasta");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return false;
    }

    public static void nombreApellidoIncorrecto() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Error al ingresar los datos\nEl nombre o el apellido son incorrectos.\n!Por favor, ingrese los datos correctamente¡");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    public static void codigoInvalido(){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error en el código ingresado");
        alert.setTitle(null);
        alert.setContentText("Por favor, corrobore lo siguiente:\n" +
                "- Si la cuenta seleccionada es de Activo el código debe ser mayor a 100 y menor que 200\n" +
                "\n" + "- Si la cuenta seleccionada es de Pasivo el código debe ser mayor a 200 y menor que 300\n" +
                "\n" + "- Si la cuenta seleccionada es de PN el código debe ser mayor a 300 y menor que 400\n" +
                "\n" + "- Si la cuenta seleccionada es de R+ el código debe ser mayor a 400 y menor que 500\n" +
                "\n" + "- Si la cuenta seleccionada es de R- el código debe ser mayor a 500 y menor que 600" );
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }
    public static void imprimirLibroMayor() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Esta es una versión beta, por lo que esta funcionalidad no esta disponible en este momento");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
    }

    /*Alerta de productos*/
    public static boolean alertaCamposVaciosProducto() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText(" Hay campos vacios , verifique e intente nuevamente ");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }
    public static boolean alertaProductoRegistrado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡ Producto registrado con éxito !");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

    public static boolean alertaExisteProducto() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText(" El codigo de producto ingresado ya se encuentra registrado. \n"+
                "Verifique e intente nuevamente con diferente codigo.\n" +
                "En caso de querer modificar el producto, debe presionar el boton Modificar.");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

    public static boolean alertaProductoModificado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡ Producto modificado con éxito !");
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.showAndWait();
        return true;
    }

}
