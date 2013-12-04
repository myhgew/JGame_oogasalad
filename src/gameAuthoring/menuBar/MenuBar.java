package gameAuthoring.menuBar;

import gameAuthoring.BasicInfoTab;
import gameAuthoring.MapDesignTab;
import gameAuthoring.WaveDesignTab;
import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


public class MenuBar extends JMenuBar {
    private GameData myGameData;
    private BasicInfoTab myBasicInfoTab;
    private MapDesignTab myMapDesignTab;
    private WaveDesignTab myWaveDesignTab;
    
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/JSON");
    private Parser myParser;
    private Simulator simulator;

    public MenuBar (GameData gameData, BasicInfoTab basicInfoTab, MapDesignTab mapDesignTab, WaveDesignTab waveDesignTab) {
        add(fileMenu());
        myGameData = gameData;
        myBasicInfoTab = basicInfoTab;
        myMapDesignTab = mapDesignTab;
        myWaveDesignTab = waveDesignTab;
        simulator  = new Simulator();
    }

    private JMenu fileMenu () {
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Save") {

            @Override
            public void actionPerformed (ActionEvent e) {
                myGameData.writeToFile();
            }

        });

        menu.add(new AbstractAction("Load") {
            @Override
            public void actionPerformed (ActionEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {

                    try {
                        Scanner s = new Scanner(INPUT_CHOOSER.getSelectedFile());
                        myParser = new Parser(s);
                        myBasicInfoTab.loadJSON(myParser);
                        myMapDesignTab.loadJSON(myParser);
                        myWaveDesignTab.loadJSON(myParser);
                    }
                    catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(null,
                                                      "Error loading");
                        e1.printStackTrace();
                    }

                }

            }
        });
        menu.add(new AbstractAction("Simulate") {
            @Override
            public void actionPerformed (ActionEvent e) {
                simulator.simulate(myGameData);
            }
        });
        return menu;
    }

}
