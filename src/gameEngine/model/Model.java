package gameEngine.model;

import gameEngine.factory.gridFactory.GridFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.tower.Tower;
import gameEngine.model.tower.TowerInfo;
import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.model.warehouse.TowerWarehouse;
import gameEngine.parser.Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import jgame.impl.JGEngineInterface;


public class Model {

    /**
     * @author Yuhua
     * 
     * warehouse - store different kinds of tower, enemy warehouse
     * 
     */
    
    private Scanner scanner;
    private Parser parser;
    private GameInfo gameInfo;
    private TowerWarehouse towerWarehouse;
    private EnemyWarehouse enemyWarehouse;
    private GridFactory gridFactory;
    private LinkedList<Tile> path;
    private JGEngineInterface myEng;
    private Rule rule; // how each waves created, ruleStart, ruleStop
    private ArrayList<ArrayList<Tile>> grid;

    public Model () {
        rule = new Rule();
    }

    public void newGame (File jsonFile) throws Exception {
        // For test convenience
        //        jsonFile = new File(System.getProperty("user.dir") + "/src/gameEngine/test/testTowerEnemyBullet/mygame.json");

        scanner = new Scanner(jsonFile);
        parser = new Parser(scanner);

        gridFactory = new GridFactory(parser);
        gridFactory.initialize();
        path = gridFactory.getPathList();
        grid = gridFactory.getGridList();

        towerWarehouse = new TowerWarehouse(parser);
        enemyWarehouse = new EnemyWarehouse(parser, this);

        gameInfo = new GameInfo(parser);
    }

    public void startGame () {
        Wave w = new Wave("1", 10, 500, 1000, enemyWarehouse);
        rule.addWave(w);
        rule.ruleStart();

    }

    //Yuhua change it
    //    public List<Tile> getPathList () {
    public LinkedList<Tile> getPathList () {
        return path;
    }

    /**
     * @author Yuhua
     * Tower Related Method
     */

    /**
     * return all kinds of TowerFactory
     */
    //edit by Jiaran to hold encapsulation by passing TowerInfo.
    public List<TowerInfo> getAllTowerInfo () {
        List<TowerInfo> result= new ArrayList<TowerInfo>();
        List<TowerFactory> factoryList=towerWarehouse.getTowerFactory();
        for(int i=0; i< factoryList.size();i++){
            result.add((TowerInfo)(factoryList.get(i)));

        }
        return result;
    }

    //Refractor method to check whether Tower exist at (x, y)
    public Tower checkTowerAtXY(int x, int y){
        int detectRange = 10;
        Detector<Tower> d= new Detector<Tower>(myEng,Tower.class);
        return d.getOneTargetInRange(x, y, detectRange);
    }

    //Jiaran: Im thinking maybe this should return a TowerInfo instead of Tower
    // Tower can implemetns Towerinfo which has getDescription,getDamage....
    // now it is not functional because no myEng, we need discussion on this.
    public TowerInfo getTowerInfo (int x, int y) {
        return (TowerInfo)checkTowerAtXY(x, y);

    }

    // Jiaran: purchase, get tower info. If something is wrong plz contact
    public boolean purchaseTower (int x, int y, String name) {
        Tile currentTile = getTile(x, y);
        if(currentTile.isEmpty()&&!currentTile.hasPath()){
            return towerWarehouse.create(x, y, name, gameInfo);
        }
        return false;
    }

    public boolean sellTower(int x, int y){
        Tower tower = checkTowerAtXY(x, y);

        if(tower != null){

            return true;
        }

        return false;
    }

    public boolean upgradeTower(int x, int y){
        Tower tower = checkTowerAtXY(x, y);

        if(tower != null){

            return true;
        }

        return false;
    }

    public boolean setTowerAttackMode(int x, int y, int attackMode){
        Tower tower = checkTowerAtXY(x, y);

        if(tower != null){

            return true;
        }

        return false;
    }

    public Tile getTile(int x, int y) {
        for(int k=0; k<grid.size(); k++) {
            ArrayList<Tile> tempArray = grid.get(k);
            for(int m=0; m<tempArray.size(); m++) {
                Tile tile = tempArray.get(m);
                if(tile.getX() <= x && tile.getEndX() >= x && tile.getY() <= y && tile.getEndY() >= y) {
                    return tile;
                }
            }
        }
        return null;
    }

    /**
     * @author Jiaran
     * GameInfo getter method
     * deleted by Jiaran based on Duvall's suggestion. Delete this when
     * every on is aware
     **/

    /*
     * Model Getter methods
     */

    public GameInfo getGameInfo() {
        return gameInfo;
    }

}
