package Controller;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerConverter extends StringConverter {
    // Default Date Pattern
    private String pattern = "MM/dd/yyyy";
    // The Date Time Converter
    private DateTimeFormatter dtFormatter;

    public DatePickerConverter() { dtFormatter = DateTimeFormatter.ofPattern(pattern) ;}

    public DatePickerConverter(String pattern) {
        this.pattern = pattern;
        dtFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    // Change String to LocalDate
    public LocalDate fromString(String text) {
        LocalDate date = null;
        if (text != null && !text.trim().isEmpty()) { date = LocalDate.parse(text, dtFormatter); }
        return date;
    }

    // Change LocalDate to String
    @Override
    public String toString(Object date) {
        String text = null;
        if (date != null)  { text = dtFormatter.format((LocalDate) date); }
        return text;
    }
}
