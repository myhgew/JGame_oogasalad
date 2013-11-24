package gameEngine.Constant;



/**
 * @author Jiaran
 *         Stores all the constant value of game engine.
 *         These constants should include all the CIDs of JGObject.
 */
public class Constant {
    public static final int PIXELSPERTILE=20;
    public static final int ENEMY_CID = 1;
    public static final int TOWER_CID = 2;
    public static final int BULLET_CID = 4;
    public static final int NORMALMAGIC_CID=0;
    
    public static final int FROZEMAGIC_ID=1;
    public static final int BOOSTMAGIC_ID=2;
    

    public static int query (Class T) {
        // System.out.println(T.getName());
        if (T.getName().equals("gameEngine.model.enemy.Enemy")) {
            return ENEMY_CID;
        }
        else if (T.getName().equals("gameEngine.model.tower.Tower")) {
            return TOWER_CID;
        }
        else return 0;
    }
}
