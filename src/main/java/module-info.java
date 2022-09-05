module sistemacontable.sistemacontable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires com.administrativos.sistema.utilidades;

    opens sistemacontable.sistemacontable to javafx.fxml;
    opens Model to javafx.fxml ;
    opens Controller to javafx.fxml;
    exports sistemacontable.sistemacontable;
    exports Controller;
    exports Model;
    exports Services;
    exports Test;
}