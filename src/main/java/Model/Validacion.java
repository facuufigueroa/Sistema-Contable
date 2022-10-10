package Model;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FormatStringConverter;

import java.text.Format;
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
            if (newText.matches("[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                return change;
            }
            return null;
        };
        campo.setTextFormatter(
                new TextFormatter<String>(filtroEmail));
    }
}
