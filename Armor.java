public class Armor extends Item
{
    private $ItemType type = $ItemType.Armor;
    private int blockableDamage;
    private int durability;

    public Armor(int blockableDamage, int durability)
    {
        this.blockableDamage = blockableDamage;
        this.durability = durability;
    }

    public int defend()
    {
        durability--;
        if(durability <= 0) Manager.destroyItem(this);
        return blockableDamage;
    }

    
}
