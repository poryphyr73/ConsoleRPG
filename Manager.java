import java.lang.Thread.State;
import java.util.Random;
import java.util.Scanner;

import javax.swing.plaf.SeparatorUI;

public class Manager
{
    private static $GameState state = $GameState.CREATE;
    private static Player player;
    private static Scanner kb;
    private static Class[] enemyTypes = { Goblin.class };
    private static Random r = new Random();

    public static void main(String[] args)
    {
        setup();
        createCharacter();
    }

    private static void setup()
    {
        player = new Player();
        kb = new Scanner(System.in);
    }

    private static void createCharacter()
    {
        System.out.println("█▀▀ █▀█ █▀▀ ▄▀█ ▀█▀ █▀▀   █▄█ █▀█ █ █ █▀█   █▀▀ █ █ ▄▀█ █▀█ ▄▀█ █▀▀ ▀█▀ █▀▀ █▀█\n█▄▄ █▀▄ ██▄ █▀█  █  ██▄    █  █▄█ █▄█ █▀▄   █▄▄ █▀█ █▀█ █▀▄ █▀█ █▄▄  █  ██▄ █▀▄");
        while(player.getStatPoints() > 0)
        {
            System.out.println("\n====================================================================\n\nADD A NEW STAT POINT TO WHICH STAT? (INTEGER, CURRENT POINTS: " + player.getStatPoints() + "):");
            for(int i = 0; i < 1; i++)
            {
                System.out.println(player.getStatName(i) + " (" + (i + 1) + "), CURRENT: " + player.getStatValue(i));
            }

            int stat = kb.nextInt();

            if(stat <= 1) player.incrementStat(stat - 1);
            else System.out.println("INVALID STAT, TRY AGAIN");
        }
    }

    private static void randomEncounter()
    {
        Class enemyClass = enemyTypes[r.nextInt(enemyTypes.length - 1)];
        System.out.println("YOU ENCOUNTER A WILD " + enemyClass.getName());
    }

    public static void destroyItem(Item c)
    {
        c = null;
    }

    public static void setNewState($GameState newState)
    {
        state = newState;
    }
}