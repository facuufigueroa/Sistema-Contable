package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CambiarFecha {
    public static java.sql.Date localDateToDate(LocalDate localDate){
        return java.sql.Date.valueOf( localDate );
    }
    public static LocalDate dateToLocalDate(java.sql.Date date){
        return date.toLocalDate();
    }


    public static String mostrarFecha(LocalDate fecha){
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatterLocalDate.format(fecha);
    }


}
