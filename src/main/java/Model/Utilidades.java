package Model;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class Utilidades {
    public static boolean estaCampoVacio(TextField textField){ return (obtenerValor(textField)).isEmpty(); }
    public static boolean sonStringIguales(String valor1, String valor2){ return valor1.equals(valor2); }
    public static String obtenerValor(TextField textField){ return textField.getText(); }
    public static void cargarValor(TextField textField, String valor){ textField.setText(valor); }
    public static boolean sonEnterosIguales(Integer valor1, Integer valor2){ return valor1.equals(valor2); }
    public static boolean sonEnterosIguales(int valor1, int valor2){ return valor1 == valor2; }
    public static String capitalizarTexto(String texto){ return texto.substring(0,1).toUpperCase() + texto.substring(1).toLowerCase(); }

    public static void deshabilitarBoton(Button button){ button.setDisable(true); }
    public static void habilitarBoton(Button button){ button.setDisable(false); }

    public static void ocultarBoton(Button button){ button.setVisible(false); }
    public static void mostrarBoton(Button button){ button.setVisible(true); }
}
