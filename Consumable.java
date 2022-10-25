public class Consumable extends Item
{
    private $ItemType type = $ItemType.Consumable;
    private int healthBoost;

    public Consumable(int healthBoost)
    {
        super();
        this.healthBoost = healthBoost;
    }
}
