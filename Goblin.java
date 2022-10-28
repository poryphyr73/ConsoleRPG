import java.util.Random;
public class Goblin extends Enemy
{
    private Random r = new Random();
    private static Player p;
    
    public Goblin()
    {
        super(10, 50, 5, 3, 1);
        p = Manager.getPlayerAddress();
    }

    public int primaryAttack()
    {
        System.out.println("THE GOBLIN ATTACKS WITH IT'S TRUSTY SABER...");
        int dmg = r.nextInt(getAttackDamage() - 1, getAttackDamage() + 1) * (p.getArmorClass() >= r.nextInt(100) ? 0 : 1);
        if(dmg == 0) System.out.println("THE GOBLIN'S ATTACK MISSES.");
        else System.out.println("THE GOBLIN'S ATTACK HITS! YOU TAKE " + dmg + " DAMAGE (CURRENT HEALTH: " + (p.getHealth() - dmg) + "/" + p.getMaxHealth() + ").");
        return dmg;
    }

    public int secondaryAttack()
    {
        System.out.println("THE GOBLIN ATTACKS WITH IT'S FLIMSY BOW...");
        int dmg = r.nextInt(getAttackDamage() - 2, getAttackDamage()) * (p.getArmorClass() - 5 >= r.nextInt(100) ? 0 : 1);
        if(dmg == 0) System.out.println("THE GOBLIN'S ATTACK MISSES.");
        else System.out.println("THE GOBLIN'S ATTACK HITS! YOU TAKE " + dmg + " DAMAGE (CURRENT HEALTH: " + (p.getHealth() - dmg) + "/" + p.getMaxHealth() + ").");
        return dmg;
    }

    public void heal()
    {
        System.out.println("THE GOBLIN HEALS 2 HEALTH.");
        super.changeHealth(2);
    }

    public void takeDamage(int k)
    {
        System.out.println("THE GOBLIN TAKES " + k + " DAMAGE.");
        super.changeHealth(-k);
    }

    public String getIcon()
    {
        return "";
    }
}
