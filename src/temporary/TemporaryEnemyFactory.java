package temporary;

/**
 * @author Jiaran
 * For testing enemy generatation
 *
 */
public class TemporaryEnemyFactory {
    
    public TemporaryEnemy create(){
        return new TemporaryEnemy(null, false, 100, 400 , 0, null);
    }
}
