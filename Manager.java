import java.util.Random;
import java.util.Scanner;

public class Manager
{
    private static $GameState state = $GameState.CREATE;
    private static Player player;
    private static Scanner kb;
    private static Enemy[] enemyTypes = { new Goblin() };
    private static Random r = new Random();
    private static Enemy currentEnemy;

    public static void main(String[] args)
    {
        setup();
        createCharacter();

        randomEncounter();
    }

    private static void setup()
    {
        player = new Player();
        kb = new Scanner(System.in);
    }

    private static void createCharacter()
    {
        System.out.println("█▀▀ █▀█ █▀▀ ▄▀█ ▀█▀ █▀▀   █▄█ █▀█ █ █ █▀█   █▀▀ █ █ ▄▀█ █▀█ ▄▀█ █▀▀ ▀█▀ █▀▀ █▀█\n█▄▄ █▀▄ ██▄ █▀█  █  ██▄    █  █▄█ █▄█ █▀▄   █▄▄ █▀█ █▀█ █▀▄ █▀█ █▄▄  █  ██▄ █▀▄\n");
        System.out.print("WHAT IS YOUR NAME? ");
        player.setName(kb.nextLine());
        System.out.println("\nWELCOME TO THE DUNGEON " + (player.getName()).toUpperCase());
        
        while(player.getStatPoints() > 0)
        {
            System.out.println("\n====================================================================\n\nADD A NEW STAT POINT TO WHICH STAT? (INTEGER, CURRENT POINTS: " + player.getStatPoints() + "):");
            for(int i = 0; i < player.getStatsArray().length; i++)
            {
                System.out.println(player.getStatName(i) + " (" + (i + 1) + "), CURRENT: " + player.getStatValue(i));
            }

            int stat = kb.nextInt();

            if(stat <= player.getStatsArray().length) player.incrementStat(stat - 1);
            else System.out.println("INVALID STAT, TRY AGAIN");
        }

        System.out.println("\n====================================================================\n");
    }

    private static void randomEncounter()
    {
        int i = r.nextInt(enemyTypes.length);
        Enemy cur = enemyTypes[i];
        cur.reset();
        String enemyClassName = cur.getClass().getName();
        System.out.println("YOU ENCOUNTER A WILD " + enemyClassName.toUpperCase());
    }

    public static void destroyItem(Item c)
    {
        c = null;
    }

    public static void setNewState($GameState newState)
    {
        state = newState;
    }

    public static Player getPlayerAddress()
    {
        return player;
    }
}