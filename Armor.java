public class Armor extends Item
{
    private $ItemType type = $ItemType.Armor;
    private int armorClass;
    private int durability;

    public Armor(int armorClass, int durability)
    {
        this.armorClass = armorClass;
        this.durability = durability;
    }

    public int defend()
    {
        durability--;
        if(durability <= 0) Manager.destroyItem(this);
        System.out.println("YOUR ARMOR BROKE ON IMPACT. NO DEFENSE");
        return armorClass;
    }
}
