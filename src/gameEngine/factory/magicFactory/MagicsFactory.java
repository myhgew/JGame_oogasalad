package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IEMagicable;
import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.ITMagicable;
import java.util.HashMap;


/*
 * wenxin shi
 */
public class MagicsFactory {

    private static MagicsFactory myMagicFactory;
    private HashMap<Integer, IMagicFactory> myFactoryMap = new HashMap<Integer, IMagicFactory>();
    
    
    private MagicsFactory () {
        myFactoryMap.put(EFrozeFactory.ID, new EFrozeFactory());
        myFactoryMap.put(TBoostFactory.ID, new TBoostFactory());
    }

    public static MagicsFactory getInstance () {
        if (myMagicFactory == null)
            myMagicFactory = new MagicsFactory();
        return myMagicFactory;
    }

    /**
     * This method is for the same magic can't overlap
     * 
     * @param target
     * @param newMagicIds
     * @param currentMagicIds
     */
    public void createEnemyMagics (IEMagicable target, int newMagicIds, int currentMagicIds) {
        newMagicIds = (~currentMagicIds) & newMagicIds;
        int temp=newMagicIds;
        int mask=1;
        for (int i = 0; newMagicIds > 0; i++) {
            IMagicFactory factory = myFactoryMap.get(temp&mask);
            if (factory != null) {
                factory.createMagicInstance(target,null);
            }
            newMagicIds = newMagicIds >> 1;
            mask=mask<<1;
        }
    }

    /**
     * This method is for generating the same magic can overlap
     * 
     * @param target
     * @param newMagicIds
     */

    public void createTowerMagics (ITMagicable target,IMagicable sender ,int newMagicIds) {
        int temp=newMagicIds;
        int mask=1;
        for (int i = 0; newMagicIds > 0; i++) {
            IMagicFactory factory = myFactoryMap.get(temp&mask);
            if (factory != null) {
                factory.createMagicInstance(target,sender);
            }
            newMagicIds = newMagicIds >> 1;
            mask=mask<<1;
        }
    }
}