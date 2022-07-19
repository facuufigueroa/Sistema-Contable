module sistemacontable.sistemacontable {
    requires javafx.controls;
    requires javafx.fxml;


    opens sistemacontable.sistemacontable to javafx.fxml;
    exports sistemacontable.sistemacontable;
}