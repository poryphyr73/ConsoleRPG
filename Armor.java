public class Armor extends Item
{
    private int armorClass;
    private int durability;
    private String name;

    public Armor(String name, int armorClass, int durability, int types)
    {
        super(types);
        this.name = name;
        this.armorClass = armorClass;
        this.durability = durability;
    }

    public Armor(String name, int armorClass, int durability)
    {
        this.name = name;
        this.armorClass = armorClass;
        this.durability = durability;
    }

    public String getName()
    {
        return name;
    }

    public int defend()
    {
        durability--;
        if(durability <= 0) Manager.destroyItem(this);
        System.out.println("YOUR ARMOR BROKE ON IMPACT. NO DEFENSE");
        return armorClass;
    }

    public String toString()
    {
        return ("a " + name + " with and armor class of " + armorClass + "! it can take " + durability + " more hits and has resistance to " + super.toString()).toUpperCase();
    }
}
