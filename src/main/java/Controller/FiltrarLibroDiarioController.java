package Controller;
import Model.ViewFuntionality;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

public class FiltrarLibroDiarioController extends ViewFuntionality {
    @FXML private Button btnBuscar;
    @FXML private DatePicker fechaDesde;
    @FXML private DatePicker fechaHasta;

    public FiltrarLibroDiarioController(){}


    @FXML
    public void obtenerFechaDesde(){
        getFechaDesde().getValue();
    }

    public Button getBtnBuscar() { return btnBuscar; }
    public DatePicker getFechaDesde() { return fechaDesde; }
    public DatePicker getFechaHasta() { return fechaHasta; }
}
