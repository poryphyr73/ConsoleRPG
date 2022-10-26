import java.util.HashMap;

public class Player 
{
    private String name;
    private int maxHealth, health;
    private int statPoints;
    private int exp, level;
    private int[] expToLevel = new int[10];
    private HashMap<Integer, Item> inventory = new HashMap<Integer, Item>();
    private int[] skills = new int[1]; 
    private String[] skillsNames = new String[1];

    public Player()
    {
        maxHealth = 10;
        health = maxHealth;
        statPoints = 10;

        skills[0] = 10;
        skillsNames[0] = "STRENGTH";

        System.out.print("Your current max health: " + maxHealth + ", Your stats: ");
        for(int i = 0; i < skills.length; i++)
        {
            System.out.print(skillsNames[i] + " " + skills[i] + "\t");
        }
        System.out.println("\n");
    }

    private void takeDamage(int damage)
    {
        health -= damage;
        if(health <= 0) Manager.setNewState($GameState.BATTLE_LOSS);
    }

    public int getStatPoints()
    {
        return statPoints;
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

    public String getStatName(int i)
    {
        return skillsNames[i];
    }

    public int getStatValue(int i)
    {
        return skills[i];
    }
}
