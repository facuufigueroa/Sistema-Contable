package Controller;

import Model.Cuenta;
import Model.ViewFuntionality;
import Services.ServicePDC;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CuentaController extends ViewFuntionality {

    @FXML private Button btnMinimize;
    @FXML private Button btnClose;
    @FXML private Button btnVolver;
    @FXML private Button btnAgregarCuenta;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCodigo;
    @FXML private ComboBox cbbRecibeSaldo;
    @FXML private ComboBox cbbTipo;
    @FXML private TableView tableCuentas;

    private CuentaController cuentaController;

    private ServicePDC servicePDC = new ServicePDC();

    public void listarCuentas(){
        ArrayList <Cuenta> arrayList = servicePDC.listCuentas();
        System.out.println(servicePDC.listCuentas());
        for (Cuenta cuenta : arrayList) {

            System.out.println("codigo:" + cuenta.getCodigo());
            System.out.println("codigo:" + cuenta.getNombre());
            System.out.println("----------------------------");
        }

    }





    public Button getBtnMinimize() {
        return btnMinimize;
    }

    public void setBtnMinimize(Button btnMinimize) {
        this.btnMinimize = btnMinimize;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public CuentaController getCuentaController() {
        return cuentaController;
    }

    public void setCuentaController(CuentaController cuentaController) {
        this.cuentaController = cuentaController;
    }
}
