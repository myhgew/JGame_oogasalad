package gameEngine.view.gameFrame.menu;
import gameEngine.view.StyleConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class ReturnToMainMenuItem extends AbstractAction{
    private MenuActions menuActions;
    public ReturnToMainMenuItem (MenuActions menuActions) {

        super(StyleConstants.resourceBundle.getString("ReturnToMainMenu"));

        this.menuActions = menuActions;
    }


    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.goToMainMenu();
        
    }

}