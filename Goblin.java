import java.util.HashMap;
import java.util.Random;
public class Goblin extends Enemy
{
    private Random r = new Random();
    Player player = Manager.getPlayerAddress();
    
    public Goblin()
    {
        super(10, 50, 5, 3, 1);
    }

    public int swordAttack()
    {
        System.out.println("THE GOBLIN ATTACKS WITH IT'S TRUSTY SABER...");
        int dmg = r.nextInt(getAttackDamage() - 1, getAttackDamage() + 1) * (player.getArmorClass() >= r.nextInt(100) ? 0 : 1);
        if(dmg == 0) System.out.println("THE GOBLIN'S ATTACK MISSES");
        else System.out.println("THE GOBLIN'S ATTACK HITS! YOU TAKE " + dmg + " DAMAGE");
        return dmg;
    }
}
