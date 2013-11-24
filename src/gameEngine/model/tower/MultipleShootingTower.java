package gameEngine.model.tower;

public class MultipleShootingTower extends DefaultTower {

    public MultipleShootingTower (double damage,
                                  double attackSpeed,
                                  int attackMode,
                                  int attackAmount,
                                  double range,
                                  double cost,
                                  double recyclePrice,
                                  String description,
                                  
                                  String type,
                                  String id,
                                  boolean unique_id,
                                  double x,
                                  double y,
                                  int collisionid,
                                  String image) {
        super(damage, attackSpeed, attackMode, range, cost, recyclePrice, description, 
              type, id, unique_id, x, y,
              collisionid, image);
        
        this.attackAmount = attackAmount;
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        
        info.put("Attack Amount", String.valueOf(attackAmount));

        info.put("Upgrade Attack Amount", String.valueOf(attackAmount*upgradeFactor));
    }
    
    @Override
    public void upgrade () {
        upgrade(upgradeFactor);
    }

    @Override
    public void downgrade(){
        downgrade(upgradeFactor);
    }
    
    @Override
    public void upgrade (double factor) {
        this.attackAmount *= factor;
        super.upgrade(factor);
    }
    
    @Override
    public void downgrade (double factor) {
        this.attackAmount /= factor;
        super.downgrade(factor);
    }

}
