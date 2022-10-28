public class Consumable extends Item
{
    private $ItemType type = $ItemType.Consumable;
    private int healthBoost;
    private String name;

    public Consumable(String name, int healthBoost)
    {
        super();
        this.name = name;
        this.healthBoost = healthBoost;
    }

    public int getHealthBoost()
    {
        return healthBoost;
    }

    public String toString()
    {
        return "+" + healthBoost + "HP " + name;
    }
}
