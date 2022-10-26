package Controller;
import Model.TablaVistaAsiento;
import Model.User;
import Model.ViewFuntionality;
import Services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class ListaUsuariosController extends ViewFuntionality implements Initializable {
    @FXML private TableView<User> tablaUsuarios;
    @FXML private TableColumn<User, String> columnNombre;
    @FXML private TableColumn<User, String> columnApellido;
    @FXML private TableColumn<User, String> columnEmail;
    @FXML private TableColumn<User, String> columnRol;

    private Service service = new Service();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarTablaUsuarios();
    }

    private void iniciarTablaUsuarios() {

        ArrayList users = getService().obtenerUsuarios();
        ObservableList<User> usersObservableList = FXCollections.observableArrayList(users);
        columnNombre.setCellValueFactory(new PropertyValueFactory<User, String>("nombre"));
        columnApellido.setCellValueFactory(new PropertyValueFactory<User, String>("apellido"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        columnRol.setCellValueFactory(new PropertyValueFactory<User, String>("rol"));

        tablaUsuarios.setItems(usersObservableList);
    }

    @FXML
    public void accionVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/home-principal.fxml"));
        Parent parent = fxmlLoader.load();
        MainController controller = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        Stage usersStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        controller.setVentana(usersStage);
        controller.hideStage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Icono.png")));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public TableView<User> getTablaUsuarios() {
        return tablaUsuarios;
    }
    public void setTablaUsuarios(TableView<User> tablaUsuarios) {
        this.tablaUsuarios = tablaUsuarios;
    }
    public Service getService() {
        return service;
    }

    public void hideStage() { getVentana().hide(); }
}
