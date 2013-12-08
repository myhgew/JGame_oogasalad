package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.inputAndDisplay.InputAndDisplayFrame;
import gameEngine.view.gameFrame.inputAndDisplay.InputSender;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;
import gameEngine.view.gameFrame.tools.store.StorePanel;
import gameEngine.view.gameFrame.towerUpdater.TowerUpgrader;
import gameEngine.controller.Controller;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj Alex Zhu
 * 
 */
public class GameFrame extends JFrame implements GameInitializable {

    private View view;
    private InputAndDisplayFrame cheatCodeFrame;
    private StorePanel storePanel;
    private InfoDisplayPanel infoPanel;
    private CanvasPanel canvasPanel;
    private TowerUpgrader towerUpgrader;
    private ItemPurchaser itemPurchaser;
    private Map<String, KeyActivationItem> gameKeyActivationItems;
    private Collection<GameInitializable> gameInitializerItems;
    private Collection<GameUpdatable> gameUpdatables;

    /**
     * @param controller facilitates communication between view and model
     * @param view
     */
    public GameFrame (Controller controller, final View view) {
        super();

        this.view = view;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cheatCodeFrame = addCheatCodeFrame(view);

        this.gameKeyActivationItems = new HashMap();
        gameKeyActivationItems.put("C", cheatCodeFrame);

        infoPanel = addInfoDisplay();
        towerUpgrader = new TowerUpgrader(infoPanel, this, view);
        itemPurchaser = new ItemPurchaser(view, this);
        storePanel = addStorePanel(towerUpgrader, itemPurchaser);
        gameInitializerItems = new ArrayList();
        gameUpdatables = new ArrayList();
        gameUpdatables.add(storePanel);
        addGameTools(infoPanel, storePanel);

        setJMenuBar(new Menu(view));
    }

    private InputAndDisplayFrame addCheatCodeFrame (final View view) {
        return new InputAndDisplayFrame("Cheat Sheet", new InputSender() {
            @Override
            public void submit (String cheat) {
                view.activateCheat(cheat);
            }
        });
    }

    public void showGame () {
        createGame();
        pack();
        setVisible(true);
    }

    public void createGame () {
        gameInitializerItems.add(this);
        canvasPanel =
                new CanvasPanel(view, itemPurchaser, towerUpgrader, gameInitializerItems,
                                gameUpdatables, gameKeyActivationItems);
        this.add(canvasPanel, BorderLayout.WEST);

        towerUpgrader.createRangeDisplay();
    }

    /**
     * Create the store of Towers
     * 
     * @param storePanel
     * @param infoPanel
     */
    private void addGameTools (InfoDisplayPanel infoPanel, StorePanel storePanel) {
        JPanel tools = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        tools.setLayout(borderLayout);
        tools.add(infoPanel, BorderLayout.CENTER);
        tools.add(storePanel, BorderLayout.PAGE_START);
        gameInitializerItems.add(storePanel);
        this.add(tools, BorderLayout.EAST);
    }

    private StorePanel addStorePanel (TowerUpgrader towerUpgrader, ItemPurchaser itemPurchaser) {
        StorePanel storePanel = new StorePanel(view, towerUpgrader, itemPurchaser);
        return storePanel;
    }

    private InfoDisplayPanel addInfoDisplay () {
        InfoDisplayPanel infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                .getString("ItemInfo"));
        return infoPanel;
    }

    @Override
    public void initialize () {
        infoPanel.setVisible(true);
        this.pack();

    }

    /**
     * Destroys the jgame instance so that it can be reloaded
     */

    public void quitGame () {
        canvasPanel.quitGame();
    }

    public void endGame () {
        infoPanel.clearDisplay();
        canvasPanel.endGame();
        storePanel.closeStore();
    }
}
