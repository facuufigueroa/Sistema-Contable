module sistemacontable.sistemacontable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires intellij.jasper.report.support;
    requires jasperreports;

    opens sistemacontable.sistemacontable to javafx.fxml;
    opens Model to javafx.fxml ,javafx.base;
    exports sistemacontable.sistemacontable;
    exports Controller;
    opens Controller to javafx.fxml;


}