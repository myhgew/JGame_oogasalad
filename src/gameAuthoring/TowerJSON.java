package gameAuthoring;

import gameEngine.parser.JSONLibrary.JSONObject;


public class TowerJSON extends JSONObject {

    public TowerJSON (String gameName, String imagePath, int damage, int attackSpeed, int range, int cost, int recyclePrice) {
        this.put("id", gameName);
        this.put("image", imagePath);
        this.put("damage", damage);
        this.put("attackSpeed", attackSpeed);
        this.put("range", range);
        this.put("cost", cost);
        this.put("recyclePrice", recyclePrice);    
    }

}