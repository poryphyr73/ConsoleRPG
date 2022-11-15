public class Consumable extends Item
{
    private int healthBoost;
    private String name;

    public Consumable(String name, int healthBoost, int types)
    {
        super(types);
        this.name = name;
        this.healthBoost = healthBoost;
    }

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
