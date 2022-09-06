package Model;
import javafx.scene.control.TextField;
public class Utilidades {
    public static boolean estaCampoVacio(TextField textField){ return (obtenerValor(textField)).isEmpty(); }
    public static boolean sonStringIguales(String valor1, String valor2){ return valor1.equals(valor2); }
    public static String obtenerValor(TextField textField){ return textField.getText(); }
    public static void cargarValor(TextField textField, String valor){ textField.setText(valor); }
    public static boolean sonEnterosIguales(Integer valor1, Integer valor2){ return valor1.equals(valor2); }
    public static boolean sonEnterosIguales(int valor1, int valor2){ return valor1 == valor2; }
}
