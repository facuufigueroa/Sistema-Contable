package Model;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.LongStringConverter;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validacion {
    public static boolean esTexto(String text){
        boolean esTexto;
        Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
        Matcher matcher = pattern.matcher(text);
        esTexto = matcher.matches();
        return esTexto;
    }
    public static boolean esNumero(String text){
        boolean esSoloNumerico;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        esSoloNumerico = matcher.matches();
        return  esSoloNumerico;
    }
    public static boolean esNumeroFlotante(String text, String precisionPostDecimal){
        boolean esNumeroFlotante;
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{0,"+precisionPostDecimal+"}$");
        Matcher matcher = pattern.matcher(text);
        esNumeroFlotante = matcher.matches();
        return  esNumeroFlotante;
    }
    public static boolean validarEmail(String email){
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(email);
        return matcher.find();
    }

    public static void validarCampoEmail(TextField campo) {
        UnaryOperator<TextFormatter.Change> filtroEmail = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})+")) {
                return change;
            }
            return null;
        };
        campo.setTextFormatter(new TextFormatter<String>(filtroEmail));
    }
    public static void validarSoloLetras(TextField textField) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^[a-zA-Z]+")) {
                return change;
            }
            return null;
        };
        textField.setTextFormatter(new TextFormatter<String>(integerFilter));
    }
    public static void limitarCantidadCaracteresYSoloNumero(final TextField txt, final int tamanoMaximo) {
        UnaryOperator<TextFormatter.Change> integerFilter = cambio -> {
            String textoNuevo = cambio.getControlNewText();
            if (textoNuevo.matches("\\d*")) { //Verifica que sea solo numero
                if (cambio.isContentChange()) { //Verifica que la longitud del texto no sea mayor a tamanoMaximo
                    int nuevaLongitud = cambio.getControlNewText().length();
                    if (nuevaLongitud > tamanoMaximo) {
                        String trimmedText = cambio.getControlNewText().substring(0, tamanoMaximo);
                        cambio.setText(trimmedText);
                        int viejaLongitud = cambio.getControlText().length();
                        cambio.setRange(0, viejaLongitud);
                    }
                }
                return cambio;
            }
            return null;
        };
        txt.setTextFormatter(new TextFormatter<Long>(new LongStringConverter(), null, integerFilter));
    }
    public static void limitarCamposDeTexto(final TextField campoTexto, final int tamanoMaximo){
        UnaryOperator<TextFormatter.Change> textLimitFilter = change -> {
            if (change.isContentChange()) {
                int newLength = change.getControlNewText().length();
                if (newLength > tamanoMaximo) {
                    String trimmedText = change.getControlNewText().substring(0, tamanoMaximo);
                    change.setText(trimmedText);
                    int oldLength = change.getControlText().length();
                    change.setRange(0, oldLength);
                }
            }
            return change;
        };
        campoTexto.setTextFormatter(new TextFormatter(textLimitFilter));
    }
}
