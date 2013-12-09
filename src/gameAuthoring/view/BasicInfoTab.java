package gameAuthoring.view;

import gameAuthoring.JSONObjects.ResourceJSONObject;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


/**
 * Subclass of Tab that contains Swing components to input or load basic game information such as
 * game name, starting quantity of gold, splash image, etc.
 * 
 * 
 * 
 */
public class BasicInfoTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");

    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;
    private JTextField altGoldText;
    private JTextField altLivesText;

    private JLabel mySplashImageLabel;
    private String mySplashImage;
    private JButton simulateButton;

    private AudioLabel myAudioLabel;
    private ImageLabel myImageLabel;

    private JPanel myMainPanel;
    private JPanel mySubPanel;

    public BasicInfoTab () {

    }

    // TO DO: Get rid of magic number
    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        mySubPanel = new JPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        mySubPanel.setOpaque(false);
        JLabel gameName = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoGameName"));
        gameName.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gameName.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoGameNameTip"));

        JLabel gold = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoGold"));
        gold.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gold.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoGoldTip"));

        JLabel lives = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoLives"));
        lives.setFont(StyleConstants.DEFAULT_BODY_FONT);
        lives.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoLivesTip"));

        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoTitle"));
        title.setFont(StyleConstants.DEFAULT_BODY_FONT);

        JLabel bullet = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoBullet"));
        bullet.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoBulletTip"));
        bullet.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myImageLabel = new ImageLabel();
        myImageLabel.setMutableStatusTrue();
        myImageLabel.setPreferredSize(new Dimension(50, 50));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        myImageLabel.setBorder(border);

        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        title.setForeground(new Color(80, 80, 80));
        myMainPanel.add(title, "span 2");

        JButton setSplashImageButton = new JButton("Choose Splash Image");
        setSplashImageButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        setSplashImageButton
                .setToolTipText("Select a Splash Image to be displayed before your game begins.");
        setSplashImageButton.addMouseListener(setSplashImageListener());

        mySplashImageLabel = new JLabel();

        JButton setInfoButton = new JButton("Set Info");
        setInfoButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        setInfoButton
                .setToolTipText("Once all fields have been completed, click the submit button. You can then proceed to 'preview' your game.");
        setInfoButton.addMouseListener(setInfoListener());

        myGameName = new JTextField();
        myGameName.setPreferredSize(new Dimension(200, 30));
        myGold = new JTextField();
        JLabel altGoldLabel = new JLabel("Alternative Gold Name:");
        altGoldLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        altGoldLabel.setToolTipText("If you want, choose another name for your currency.");
        altGoldText = new JTextField();
        altGoldText.setPreferredSize(new Dimension(200, 30));
        altGoldText.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myGold.setPreferredSize(new Dimension(200, 30));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));
        JLabel altLivesLabel = new JLabel("Alternative Lives Name:");
        altLivesLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        altLivesLabel.setToolTipText("If you want, choose another name for your lives.");
        altLivesText = new JTextField();
        altLivesText.setPreferredSize(new Dimension(200, 30));
        altLivesText.setFont(StyleConstants.DEFAULT_BODY_FONT);

        myAudioLabel = new AudioLabel();
        JLabel audioLabel = new JLabel("Background Audio:");
        audioLabel.setToolTipText("Select an audio to be played in the background of your game");
        myAudioLabel.setMutableStatusTrue();
        audioLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);

        addGameName();
        addGold();
        addLives();
        addBackgroundAudio();
        addBullet();

        mySubPanel.add(myGameName);
        mySubPanel.add(gold);
        mySubPanel.add(myGold);
        mySubPanel.add(altGoldLabel);
        mySubPanel.add(altGoldText);
        mySubPanel.add(lives);
        mySubPanel.add(myLives);
        mySubPanel.add(altLivesLabel);
        mySubPanel.add(altLivesText);
        mySubPanel.add(audioLabel);
        mySubPanel.add(myAudioLabel);
        mySubPanel.add(bullet);
        mySubPanel.add(myImageLabel);
        mySubPanel.add(setSplashImageButton);
        mySubPanel.add(mySplashImageLabel);
        mySubPanel.add(setInfoButton);
        // subPanel.add(simulateButton);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        mySubPanel.setBorder(b);
        addTitle();
        myMainPanel.add(mySubPanel, "align center");

        return myMainPanel;
    }

    public void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoTitle"));
        title.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myMainPanel.add(title);
    }

    public void addGameName () {
        JLabel gameName = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoGameName"));
        gameName.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gameName.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoGameNameTip"));
        mySubPanel.add(gameName);
    }

    public void addGold () {
        JLabel gold = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoGold"));
        gold.setFont(StyleConstants.DEFAULT_BODY_FONT);
        gold.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoGoldTip"));
        myGold = new JTextField();
        myGold.setPreferredSize(new Dimension(200, 30));
        JLabel altGoldLabel = new JLabel("Alternative Gold Name:");
        altGoldLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        altGoldLabel.setToolTipText("If you want, choose another name for your currency.");
        altGoldText = new JTextField();
        altGoldText.setPreferredSize(new Dimension(200, 30));
        altGoldText.setFont(StyleConstants.DEFAULT_BODY_FONT);
        mySubPanel.add(myGameName);
        mySubPanel.add(gold);
        mySubPanel.add(altGoldLabel);
        mySubPanel.add(altGoldText);
    }

    public void addLives () {
        JLabel lives = new JLabel(StyleConstants.resourceBundle.getString("BasicInfoLives"));
        lives.setFont(StyleConstants.DEFAULT_BODY_FONT);
        lives.setToolTipText(StyleConstants.resourceBundle.getString("BasicInfoLivesTip"));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));
        JLabel altLivesLabel = new JLabel("Alternative Lives Name:");
        altLivesLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        altLivesLabel.setToolTipText("If you want, choose another name for your lives.");
        altLivesText = new JTextField();
        altLivesText.setPreferredSize(new Dimension(200, 30));
        altLivesText.setFont(StyleConstants.DEFAULT_BODY_FONT);
        mySubPanel.add(lives);
        mySubPanel.add(myLives);
        mySubPanel.add(altLivesLabel);
        mySubPanel.add(altLivesText);
    }

    public void addBackgroundAudio () {
        myAudioLabel = new AudioLabel();
        JLabel audioLabel = new JLabel("Background Audio:");
        audioLabel.setToolTipText("Select an audio to be played in the background of your game");
        myAudioLabel.setMutableStatusTrue();
        audioLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        mySubPanel.add(audioLabel);
        mySubPanel.add(myAudioLabel);
    }

    public void addBullet () {

    }

    public void loadJSON (Parser p) {
        try {
            myGameName.setText(p.getString("name"));
            myGold.setText(String.valueOf(p.getInt("gold")));
            myLives.setText(String.valueOf(p.getInt("numberOfLives")));
            mySplashImage = p.getString("splashImage");
            mySplashImageLabel.setText(mySplashImage);

            setData();
        }

        catch (NumberFormatException n) {
            JOptionPane.showMessageDialog(null,
                                          "File invalid!");
        }
    }

    public MouseAdapter setInfoListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setData();
            }
        };
        return listener;

    }

    // public MouseAdapter setSimulateListener () {
    // MouseAdapter listener = new MouseAdapter() {
    // @Override
    // public void mouseClicked (MouseEvent e) {
    // Simulator simulator = new Simulator();
    // simulator.simulate(myGameData);
    // }
    // };
    // return listener;
    //
    // }

    public void setBackgroundAudio (AudioLabel audio) {
        myAudioLabel = audio;
    }

    private void setData () {
        int gold = Integer.parseInt(myGold.getText());
        int lives = Integer.parseInt(myLives.getText());
        String name = myGameName.getText();
        String bulletName = myImageLabel.getImageFile().getName();
        if (myAudioLabel != null && gold > 0 && lives > 0 && mySplashImage != null &&
            name != null && bulletName != null) {
            String goldName = altGoldText.getText();
            String livesName = altLivesText.getText();
            String splashImage = mySplashImage.substring(0, mySplashImage.length() - 4);
            BasicInformation gameDesignInfo =
                    new BasicInformation(gold, lives, splashImage, name, myAudioLabel,
                                         livesName, goldName, bulletName);
            setChanged();
            notifyObservers(gameDesignInfo);
            clearChanged();
            // activateSimmulate();
        }

        else {
            JOptionPane.showMessageDialog(null,
                                          "One or more fields invalid! Please try again.");
        }
    }

    // private void activateSimmulate () {
    // simulateButton.setEnabled(true);
    // }

    public MouseAdapter setSplashImageListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {

                    mySplashImage = INPUT_CHOOSER.getSelectedFile().getName();
                    ResourceJSONObject splashImage =
                            new ResourceJSONObject(
                                                   mySplashImage.substring(0,
                                                                           mySplashImage.length() - 4),
                                                   mySplashImage);
                    setChanged();
                    notifyObservers(splashImage);
                    clearChanged();
                    mySplashImageLabel.setText(mySplashImage);
                }

            }
        };
        return listener;
    }
}
