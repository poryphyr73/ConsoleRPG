import java.util.HashMap;
import java.util.Random;

public class Player 
{
    private String name;
    private int maxHealth, health;
    private int statPoints;
    private int exp, level;
    private int[] expToLevel = new int[10];
    private HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    private int coins;
    private String[] skillsNames = { "Strength", "Wisdom", "Dexterity" };
    private int[] skills = new int[skillsNames.length]; 
    private Armor armorEquipped;
    private Weapon weaponEquipped;
    private Random r = new Random();
    
    public Player()
    {
        maxHealth = 10;
        health = maxHealth;
        statPoints = 10;

        for (int i = 0; i < skillsNames.length; i++) skills[i] = 10;

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
        else
        {
            System.out.println("\nTHE ATTACK HITS YOUR BARE SKIN");
            return 0 + skills[2] / 10;
        } 
    }

    public int getDamageRating()
    {
        if(weaponEquipped != null) return weaponEquipped.getDamage();
        else
        {
            System.out.println("\nYOU ATTACK WITH YOUR BARE HANDS");
            return 1 * skills[0] / 10;
        } 
    }

    public String getOptions()
    {
        return "1. [ ATTACK ]\t2. [ HEAL ]\n3. [ EQUIP ]\t4. [ FLEE ]\n";
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

    //#region TURN OPTIONS

    public boolean equip()
    {
        String inv = "\nYOUR CURRENT INVENTORY:\n";

        for(int i = 0; i < inventory.size(); i++)
        {
            if(!inventory.keySet().toArray()[i].getClass().equals(Consumable.class))
            {
                inv += "\tITEM: " + inventory.keySet().toArray()[i];
                inv += ", AMOUNT REMAINING: " + inventory.get(inventory.keySet().toArray()[i]);
                inv += "\t[" + (i + 1) + "]\n";
            }
        }

        if(!inv.contains("ITEM: ")) return false;
        
        System.out.println(inv);

        int selection = Manager.getKeyBoard().nextInt();
        Item cur = (Item) inventory.keySet().toArray()[selection - 1];
        inventory.replace(cur, inventory.get(cur).intValue() - 1);
        
        if(cur.getClass().equals(Armor.class)) armorEquipped = (Armor) cur;
        else weaponEquipped = (Weapon) cur;

        System.out.println("YOU EQUIP YOUR " + cur);

        return true;
    }

    public int heal()
    {
        if(health == maxHealth) return 1;

        String inv = "\nYOUR CURRENT INVENTORY:\n";

        for(int i = 0; i < inventory.size(); i++)
        {
            if(inventory.keySet().toArray()[i].getClass().equals(Consumable.class))
            {
                inv += "\tITEM: " + inventory.keySet().toArray()[i];
                inv += ", AMOUNT REMAINING: " + inventory.get(inventory.keySet().toArray()[i]);
                inv += "\t[" + (i + 1) + "]\n";
            }
        }

        if(!inv.contains("ITEM: ")) return -1;
        
        System.out.println(inv);

        int selection = Manager.getKeyBoard().nextInt();
        Consumable cur = (Consumable) inventory.keySet().toArray()[selection - 1];
        inventory.replace(cur, inventory.get(cur).intValue() - 1);
        health += cur.getHealthBoost();
        
        if(health > maxHealth) health = maxHealth;

        System.out.println("\nHEALED " + cur.getHealthBoost() + " HEALTH. HEALTH: " + health + "/" + maxHealth);

        return 0;
    }

    public boolean tryToFlee()
    {
        System.out.print("YOUR ODDS TO FLEE ARE " + skills[2] * 2 + "/100");
        for(int i = 0; i < 3; i++)
        {
            Manager.waitTime(1000);
            System.out.print(".");
        }
        System.out.println("");
        return skills[2] * 2 >= r.nextInt(100);
    }

    //#endregion TURN OPTIONS

    public String toString()
    {
        String ret = "THE CURRENT STATS OF THE LEGENDARY WARRIOR, " + name + ".\nCURRENT MAX HEALTH: " + maxHealth + ", CURRENT STATS: ";
        
        for(int i = 0; i < skills.length; i++)
        {
            ret += skillsNames[i] + " " + skills[i] + "  ";
        }

        return ret;
    }
}
