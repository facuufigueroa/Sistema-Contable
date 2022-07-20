module sistemacontable.sistemacontable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sistemacontable.sistemacontable to javafx.fxml;
    exports sistemacontable.sistemacontable;
}