package gameEngine.model.tile;

import java.util.HashMap;
import java.util.Map;
import jgame.JGObject;
import gameEngine.Constant.Constant;
import gameEngine.model.purchase.PurchaseInfo;

/**
 * Used to create a temporary barrier in the path (ex. a puddle of water that kills enemies until it dries up)
 * @author: Harris Osserman
 */

public class TemporaryBarrier extends JGObject implements PurchaseInfo{
    private String barrierName, image;
    private double x, y, damage;
    private String description;
    private int cost, expire;
    private HashMap<String, String> info;

    
    public TemporaryBarrier(String name, String gfxname, double damage, int cost, int expire, String description, double x, double y) {
        super(name, true, x, y, Constant.BULLET_CID, gfxname);
        this.description = description;
        this.barrierName = name;        
        this.image = gfxname;
        this.expire = expire;
        this.cost = cost;
        this.damage = damage;
        info = new HashMap<String, String>();
        info.put("description", this.description);
        info.put("name", this.barrierName);
        info.put("image", this.image);
        info.put("cost", this.cost + "");
        info.put("damage", this.damage + "");
        info.put("expire", this.expire + "");
    }
        
    @Override
    public String getItemName () {
        return barrierName;
    }

    @Override
    public double getDamage () {
        return 0;
    }

    @Override
    public double getAttackSpeed () {
        return 0;
    }

    @Override
    public double getRange () {
        return 0;
    }

    @Override
    public int getCost () {
        return cost;
    }

    @Override
    public double getRecyclePrice () {
        //You can't recycle a temporary barrier (ex. a puddle of water or a ball of fire)
        return 0;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public String getImage () {
        return image;
    }

    @Override
    public Map<String, String> getInfo () {
        return info;
    }

}
