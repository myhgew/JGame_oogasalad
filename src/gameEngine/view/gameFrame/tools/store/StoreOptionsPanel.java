package gameEngine.view.gameFrame.tools.store;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.ViewConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.ItemPurchaser;
import gameEngine.view.gameFrame.tools.DisplayValue;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


/**
 * Panel that contains the inventory of
 * different options a
 * user can purchase
 * 
 * @author Lalita Maraj
 */
@SuppressWarnings("serial")
public class StoreOptionsPanel extends JPanel {
    private static final String[] DISPLAY_KEYS = { GameEngineConstant.PURCHASE_INFO_NAME,
                                                  GameEngineConstant.TOWER_DAMAGE,
                                                  GameEngineConstant.TOWER_ATTACK_SPEED,
                                                  GameEngineConstant.TOWER_ATTACK_AMOUNT,
                                                  GameEngineConstant.TOWER_RANGE,
                                                  GameEngineConstant.TOWER_MAGIC,
                                                  GameEngineConstant.TOWER_MAGIC_FACTOR,
                                                  GameEngineConstant.TOWER_BOOST_FACTOR,
                                                  GameEngineConstant.PURCHASE_INFO_COST,
                                                  GameEngineConstant.PURCHASE_INFO_DESCRIPTION };
    private static final String LAYOUT_WRAP = "wrap 4";
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_HEIGHT = 200;
    private List<StoreItemButton> storeItems;
    private View view;
    private ItemOptionsDisplayer utilities;
    private ItemPurchaser itemPurchaser;
    private Map<String, String> valuesToDisplay;
    private JPanel options;

    /**
     * @param mediator facilitates communication between view components
     * @param engineView facilitates communication between view and controller
     * @param images
     * @param utilities
     * @param itemPurchaser
     */
    protected StoreOptionsPanel (
                                 View engineView,
                                 Map<String, String> images, List<PurchaseInfo> towerInformation,
                                 ItemOptionsDisplayer utilities,
                                 ItemPurchaser itemPurchaser) {

        super();
        this.view = engineView;
        this.storeItems = new ArrayList<StoreItemButton>();
        this.utilities = utilities;
        this.itemPurchaser = itemPurchaser;
        setUIStyle();
        JPanel options = createOptionsScrollPanel();
        addStoreInventory(images, options, towerInformation);
        valuesToDisplay = new LinkedHashMap<String, String>();
        for (String str : DISPLAY_KEYS) {
            valuesToDisplay.put(str, "black");
        }
    }

    /**
     * Creates the scroll panel that will hold the
     * inventory of towers a user can purchase
     * 
     * @param mediator facilitates communication between view components
     * @return
     */
    private JPanel createOptionsScrollPanel () {

        options = new JPanel(new MigLayout(LAYOUT_WRAP));
        options.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JScrollPane scrollPane = new JScrollPane(options);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        add(scrollPane);
        return options;
    }

    /**
     * Set look and feel of the display of available towers
     */
    private void setUIStyle () {

        Border listPanelBorder =
                BorderFactory.createTitledBorder(ViewConstants.resourceBundle
                        .getString("StoreName"));
        setBorder(listPanelBorder);
    }

    /**
     * Adds buttons based on the defined towers specified by Model
     * 
     * @param options
     * 
     * @param optionsPanel panel buttons are added to
     * @param mediator facilitates communication between view components
     * @param view facilitates communication between view and model
     */
    private void addStoreInventory (Map<String, String> images,
                                    JPanel options,
                                    List<PurchaseInfo> towerInformation) {

        StoreButtonAction hoverExitAction = new StoreButtonAction() {

            @Override
            public void executeAction () {
                utilities.clearDisplay();

            }

        };
        for (final PurchaseInfo storeItem : towerInformation) {
            StoreButtonAction clickAction = new StoreButtonAction() {

                @Override
                public void executeAction () {
                    itemPurchaser.placeTower(storeItem);

                }

            };
            StoreButtonAction hoverAction = new StoreButtonAction() {
                @Override
                public void executeAction () {
                    List<DisplayValue> display = new ArrayList();
                    for (String key : valuesToDisplay.keySet()) {
                        if (storeItem.getInfo().get(key) != null) {
                            String field = key;
                            String value = storeItem.getInfo().get(key);
                            String color = valuesToDisplay.get(key);

                            display.add(new DisplayValue(field, value, color));
                        }
                    }
                    utilities.displayStoreInformation(storeItem.getInfo(), display);

                }
            };
            String imagePath = ViewConstants.IMAGE_PATH +
                               images.get(storeItem.getInfo()
                                       .get(GameEngineConstant.PURCHASE_INFO_IMAGE).trim());
            StoreItemButton towerButton =
                    new StoreItemButton(imagePath, storeItem, hoverExitAction, hoverAction,
                                        clickAction);
            options.add(towerButton);
            storeItems.add(towerButton);
        }
        this.revalidate();
    }

    /**
     * Used to update the status of each Item in store.
     * Toggles their enabled/disabled status based on the user's
     * money supply
     * 
     */
    public void updateStoreStatus () {
        for (StoreItemButton button : storeItems) {

            int money = view.getGameInfo().getGold();
            button.toggleButtonActivation(money);

        }
    }

    public void endGame () {
        options.removeAll();
        options.revalidate();
        options.repaint();
        
        storeItems = null;
    }

}
