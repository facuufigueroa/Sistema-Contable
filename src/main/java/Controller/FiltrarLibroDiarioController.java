package Controller;
import Model.ViewFuntionality;
import Reportes.LibroDiario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FiltrarLibroDiarioController extends ViewFuntionality implements Initializable {
    @FXML private Button btnBuscar;
    @FXML private DatePicker fechaDesde;
    @FXML private DatePicker fechaHasta;

    private final LocalDate calendarioDesde = LocalDate.of(2022, 9, 05);
    private final LocalDate calendarioHasta = LocalDate.now();



    public FiltrarLibroDiarioController(){}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restringirFechaHasta();
        restringirFechaDesde();
    }

    private void restringirFechaHasta(){
        String pattern = "dd/MM/yyyy";
        DatePickerConverter converter = new DatePickerConverter(pattern);
        getFechaHasta().setConverter(converter);
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        // Must call super
                        super.updateItem(item, empty);
                        // Disable all future date cells
                        if (item.isAfter(calendarioHasta)) {
                            this.setDisable(true);
                        } else if (item.isBefore(calendarioDesde)) {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };
        getFechaHasta().setDayCellFactory(dayCellFactory);
    }
    private void restringirFechaDesde(){
        String pattern = "dd/MM/yyyy";
        DatePickerConverter converter = new DatePickerConverter(pattern);
        getFechaHasta().setConverter(converter);
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        // Must call super
                        super.updateItem(item, empty);
                        // Disable all future date cells
                        if (item.isBefore(calendarioDesde)) {
                            this.setDisable(true);
                        } else if (item.isAfter(calendarioHasta)) {
                            this.setDisable(true);
                        }
                    }
                };
            }
        };
        getFechaDesde().setDayCellFactory(dayCellFactory);
    }

    public void obtenerFecha(DatePicker fecha){ fecha.getValue(); }

    @FXML
    public void accionFiltrarFecha(){
        /*if (getFechaDesde().getValue().isAfter(getFechaHasta().getValue())){
            System.out.println("Error, la fecha desde no puede ser mayor a la fecha hasta");

        }

         */
        LibroDiario libroDiario = new LibroDiario();
        libroDiario.conexionReporte();
    }

    public void hideStage(){ getVentana().hide(); }
    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/home-principal.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainController controller = fxmlLoader.getController();
        controller.setVentana(loginStage);
        controller.hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
    public Button getBtnBuscar() { return btnBuscar; }
    public DatePicker getFechaDesde() { return fechaDesde; }
    public DatePicker getFechaHasta() { return fechaHasta; }
}
