package Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**  Clase en la cual tiene la funcionalidad de cerrar y minimizar ventanas.
 *   Con esto es posible agregarle acciones a los botones cerrar y minimizar sin necesidad de hacerlo en cada controller.
 *   Posee un atributo ventana de tipo Stage y su función es poder ocultar ventanas cuando se abren otras.
 *
 *   Ejemplo: En el login al hacer click en sobre el botón registrarse, oculta la ventana del login hasta que se complete
 *            el registro de un usuario.
 *            Al terminar, se cierra la ventana de registro y se vuelve a mostrar la ventana del login
 *
 *            !!!SE PUEDE MODIFICAR!!!
 * */
public class ViewFuntionality {
    private Stage ventana;

    public Stage getVentana() { return ventana; }
    public void setVentana(Stage ventana) { this.ventana = ventana; }

    @FXML
    public void btnClose(){ System.exit(0); }
    @FXML
    public void btnMax(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint(" ");
        stage.setFullScreen(true);
    }
    @FXML
    public void actionMinStage(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    public void actionCloseStage(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
