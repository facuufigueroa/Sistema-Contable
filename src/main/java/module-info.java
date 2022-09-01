module sistemacontable.sistemacontable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens sistemacontable.sistemacontable to javafx.fxml;
    opens Model to javafx.fxml ;
    exports sistemacontable.sistemacontable;
    exports Controller;
    opens Controller to javafx.fxml;
}