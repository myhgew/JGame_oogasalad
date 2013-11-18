package gameEngine.view.initialization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import gameEngine.view.Button;
import gameEngine.view.StyleConstants;


/**
 * Used to close iniitalization Frame
 * 
 * @author Lalita Maraj
 * 
 */
public class CancelButton extends Button {
    JFrame frame;

    public CancelButton (JFrame frame) {
        super(StyleConstants.resourceBundle.getString("Cancel"));
        this.frame = frame;
        addMouseAdapter();

    }


    private void addMouseAdapter(){
        addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
                frame.dispose();
            } 
         
          }); 

    }

}
