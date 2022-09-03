package Controller;

import Model.ViewFuntionality;


public class MenuPrincipalController extends ViewFuntionality {

    private MenuPrincipalController menuPrincipalController;

    public MenuPrincipalController getMenuPrincipalController() {
        return menuPrincipalController;
    }

    public void setMenuPrincipalController(MenuPrincipalController menuPrincipalController) {
        this.menuPrincipalController = menuPrincipalController;
    }

    public void hideStage(){ getVentana().hide(); }
    public void showStage(){ getVentana().show(); }


}
