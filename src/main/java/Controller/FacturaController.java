package Controller;
import Model.Ventas.TablaGestionFactura;
import Model.ViewFuntionality;
import Services.Ventas.ServiceGestionFactura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacturaController extends ViewFuntionality implements Initializable {
    private HomeVentasController homeVentasController;
    private ServiceGestionFactura serviceGestionFactura = new ServiceGestionFactura();

    @FXML private TableView<TablaGestionFactura> tablaProductos = new TableView();
    @FXML private TableColumn<TablaGestionFactura, String> colNumFactura = new TableColumn<>();
    @FXML private TableColumn<TablaGestionFactura, String> colCliente = new TableColumn<>();
    @FXML private TableColumn<TablaGestionFactura, String> colFechaCobro = new TableColumn<>();
    @FXML private TableColumn<TablaGestionFactura, Double> colTotal = new TableColumn<>();
    @FXML private Button btnVolver;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTabla();
    }

    private void iniciarTabla() {
        colNumFactura.setCellValueFactory(new PropertyValueFactory<>("numeroFactura"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colFechaCobro.setCellValueFactory(new PropertyValueFactory<>("fechaCobro"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        actualizarDatosTabla();
    }

    private void actualizarDatosTabla(){
        ObservableList<TablaGestionFactura> listado = FXCollections.observableArrayList(serviceGestionFactura.listarFacturas());
        getTablaProductos().setItems(listado);
    }


    public void hideStage(){ getVentana().hide(); }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/menu-ventas.fxml"));
        Parent parent = fxmlLoader.load();
        setHomeVentasController(homeVentasController(fxmlLoader.getController()));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        getHomeVentasController().setVentana(homeStage);
        getHomeVentasController().hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.showAndWait();
    }
    private HomeVentasController homeVentasController(HomeVentasController controller){ return controller; }

    public HomeVentasController getHomeVentasController() { return homeVentasController; }
    public void setHomeVentasController(HomeVentasController homeVentasController) { this.homeVentasController = homeVentasController; }
    public TableView<TablaGestionFactura> getTablaProductos() { return tablaProductos; }
}
