package Controller;

import Model.ViewFuntionality;
import Reportes.ReporteFactura;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacturaController extends ViewFuntionality implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private Button btnVolver;

    public void hideStage(){ getVentana().hide(); }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {

    }

    @FXML
    public void accionPrueba(){
        ReporteFactura reporteFactura = new ReporteFactura();
        reporteFactura.loadFactura();
    }
}
