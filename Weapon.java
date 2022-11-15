import java.util.Random;

public class Weapon extends Item
{
    private String name;
    private int damage;
    private int accuracy;
    private int durability;
    Random r = new Random();

    public Weapon(String name, int damage, int accuracy, int durability, int types)
    {
        super(types);
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
        this.durability = durability;
    }

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
        return damage * (accuracy >= r.nextInt(100) ? 1 : 0);
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
        return ("The legendary weapon, " + name + "! It deals " + damage + " damage per hit (" + accuracy + "% accuracy). it has an elemental buff of " + super.toString()).toUpperCase();
    }
}
