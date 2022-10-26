import java.util.Random;

public class Weapon extends Item
{
    private String name;
    private $ItemType type = $ItemType.Weapon;
    private int damage;
    private int accuracy;
    private int durability;
    Random r = new Random();

    public Weapon(String name, int damage, int accuracy, int durability)
    {
        super();
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
        this.durability = durability;
    }

    public int getDamage()
    {
        durability--;
        if(durability <= 0) Manager.destroyItem(this);
        return damage * (accuracy >= r.nextInt(100) ? 0 : 1);
    }

    public $ItemType getType()
    {
        return type;
    }

    public int getRawDamage()
    {
        return damage;
    }

    public int getAccuracy()
    {
        return accuracy;
    }

    public int getDurability()
    {
        return durability;
    }

    public void setDamage(int i)
    {
        damage = i;
    }

    public String toString()
    {
        return "The legendary weapon, " + name + "! It deals " + damage + " damage per accurate swing (" + accuracy + "% accuracy)";
    }
}
