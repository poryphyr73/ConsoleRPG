import java.util.HashMap;

public class Player 
{
    private String name;
    private int maxHealth, health;
    private int statPoints;
    private int exp, level;
    private int[] expToLevel = new int[10];
    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    private int coins;
    private String[] skillsNames = { "Strength", "Wisdom" };
    private int[] skills = new int[skillsNames.length]; 
    private Armor armorEquipped;
    private Weapon weaponEquipped;
    
    public Player()
    {
        maxHealth = 10;
        health = maxHealth;
        statPoints = 10;

        for (int i = 0; i < skillsNames.length; i++) skills[i] = 10;

        System.out.print("Your current max health: " + maxHealth + ", Your stats: ");
        for(int i = 0; i < skills.length; i++)
        {
            System.out.print(skillsNames[i] + " " + skills[i] + "\t");
        }
        System.out.println("\n");
    }

    //#region GETTERS

    public String getName()
    {
        return name;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public int getStatPoints()
    {
        return statPoints;
    }

    public int getExp()
    {
        return exp;
    }

    public int getLevel()
    {
        return level;
    }

    public int getExpToLevel()
    {
        return expToLevel[level];
    }

    public boolean hasItem(Item cur)
    {
        return inventory.containsKey(cur);
    }

    public int getNumberItems(Item cur)
    {
        return inventory.get(cur);
    }

    public String getInventory()
    {
        String inv = "YOUR CURRENT INVENTORY:\n";

        for(int i = 0; i < inventory.size(); i++)
        {
            inv += "\tITEM: " + inventory.keySet().toArray()[i];
            inv += ", VALUE: " + inventory.entrySet().toArray()[i];
            inv += "\n";
        }

        return inv;
    }

    public String getStatName(int i)
    {
        return skillsNames[i];
    }

    public int getStatValue(int i)
    {
        return skills[i];
    }

    public String[] getStatsArray()
    {
        return skillsNames;
    }

    public int getArmorClass()
    {
        if(armorEquipped != null) return armorEquipped.defend();
        else return 0;
    }

    //#endregion GETTERS

    //#region SETTERS

    public void setName(String name)
    {
        this.name = name;
    }

    //#endregion SETTERS

    //#region MODIFIERS

    public void changeHealth(int k)
    {
        health += k;
        if(health <= 0) Manager.setNewState($GameState.BATTLE_LOSS);
    }

    public void addStatPoints(int i)
    {
        statPoints += i;
    }

    public void incrementStat(int i)
    {
        if(i < skills.length) 
        {
            skills[i]++;
            statPoints--;
        }
    }

    //#endregion MODIFIERS
}
