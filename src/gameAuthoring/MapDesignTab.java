package gameAuthoring;

import javax.swing.JPanel;


public class MapDesignTab extends Tab {

    public MapDesignTab (GameData gameData) {
        super(gameData);
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
