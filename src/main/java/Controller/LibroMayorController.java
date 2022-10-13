package Controller;
import Model.*;
import Reportes.LibroDiario;
import Services.ServiceLibroMayor;
import Services.ServicePDC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LibroMayorController extends ViewFuntionality implements Initializable {
    @FXML private TableColumn columnNumero;
    @FXML private TableColumn columnDetalle;
    @FXML private TableColumn columnDebe;
    @FXML private TableColumn columnHaber;
    @FXML private TableColumn columnSaldo;
    @FXML private Button btnMostrar;
    @FXML
    private ComboBox cbbCuentas;
    @FXML private DatePicker fechaDesde;
    @FXML private DatePicker fechaHasta;
    @FXML private TableView tablaMayor;

    private final LocalDate calendarioDesde = LocalDate.of(2022, 9, 5);

    private final LocalDate calendarioHasta = LocalDate.now();

    ServiceLibroMayor service = new ServiceLibroMayor();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarNombreCuentas();
        restringirFechaDesde();
        restringirFechaHasta();
        getFechaHasta().setEditable(false);
        getFechaDesde().setEditable(false);
    }

    private void iniciarTablaMayor() {
        //ObservableList <TablaMayor> obCuentas = FXCollections.observableArrayList(service.obtenerTablaMayor());
        columnNumero.setCellValueFactory(new PropertyValueFactory<TablaMayor, Integer>("idAsiento"));
        columnDetalle.setCellValueFactory(new PropertyValueFactory<TablaMayor, String>("detalle"));
        columnDebe.setCellValueFactory(new PropertyValueFactory<TablaMayor, Double>("debe"));
        columnHaber.setCellValueFactory(new PropertyValueFactory<TablaMayor, Double>("haber"));
        columnSaldo.setCellValueFactory(new PropertyValueFactory<TablaMayor, Double>("saldo"));
        //tablaMayor.setItems(obCuentas);
    }

    private void cargarNombreCuentas() {
        ObservableList<String> cuentas= FXCollections.observableArrayList();
        cuentas.addAll(service.traerNombreCuentas());
        cbbCuentas.setItems(cuentas);
    }

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

    public void hideStage() {
        getVentana().hide();
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
    @FXML
    public void accionFiltrarBusqueda(){
        if(getFechaDesde().getValue() == null || getFechaHasta().getValue() == null){
            Alerta.alertaFechaIncompleta();
        }
        else{
            iniciarTablaMayor();
        }
    }



    public ComboBox getCbbCuentas() {
        return cbbCuentas;
    }

    public DatePicker getFechaDesde() {
        return fechaDesde;
    }

    public DatePicker getFechaHasta() {
        return fechaHasta;
    }

    public TableView getTablaMayor() {
        return tablaMayor;
    }

    public Button getBtnMostrar() {
        return btnMostrar;
    }
}
