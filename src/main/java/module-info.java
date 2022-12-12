module sistemacontable.sistemacontable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires jasperreports;
    requires org.testng;


    opens Model.Ventas to javafx.base ;
    opens sistemacontable.sistemacontable to javafx.fxml;
    opens Model to javafx.fxml ,javafx.base;
    opens Controller to javafx.fxml;
    exports sistemacontable.sistemacontable;
    exports Controller;
}