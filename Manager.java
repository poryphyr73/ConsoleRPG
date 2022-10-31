import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Manager
{
    private static $GameState state = $GameState.CREATE;
    private static Player player = new Player();
    private static Scanner kb = new Scanner(System.in);
    private static Enemy[] enemyTypes = { new Goblin() };
    private static Enemy cur;
    private static Random r = new Random();

    public static void main(String[] args)
    {
        createCharacter();
        randomEncounter();
        state = $GameState.BATTLE;
        
        while(state != $GameState.BATTLE_LOSS)
        {
            while(state == $GameState.BATTLE)
            {
                playerTurn();
                if(state == $GameState.BATTLE) enemyTurn();
            }

            if(state == $GameState.BATTLE_WIN)
            {
                //handle rewards
                state = $GameState.DUNGEON_ENTER;
            }

            if(state == $GameState.DUNGEON_ENTER)
            {
                System.out.println("A NEW BATTLE AWAITS!");
                randomEncounter();
                state = $GameState.BATTLE;
            }            
        }
    }

    private static void createCharacter()
    {
        System.out.println("█▀▀ █▀█ █▀▀ ▄▀█ ▀█▀ █▀▀   █▄█ █▀█ █ █ █▀█   █▀▀ █ █ ▄▀█ █▀█ ▄▀█ █▀▀ ▀█▀ █▀▀ █▀█\n█▄▄ █▀▄ ██▄ █▀█  █  ██▄    █  █▄█ █▄█ █▀▄   █▄▄ █▀█ █▀█ █▀▄ █▀█ █▄▄  █  ██▄ █▀▄\n");
        System.out.print("WHAT IS YOUR NAME? ");
        player.setName(kb.nextLine());
        System.out.println("\n" + player);
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
        cur = enemyTypes[i];
        cur.reset();
        String enemyClassName = cur.getClass().getName();
        System.out.println("YOU ENCOUNTER A WILD " + enemyClassName.toUpperCase() + ".");
        //System.out.println(cur.getIcon()); TO BE IMPLEMENTED
        state = $GameState.DUNGEON_ENTER;
    }

    private static void playerTurn()
    {
        System.out.println("\nWHAT WILL YOU DO?\n" + player.getOptions());
        int selection = kb.nextInt();
        switch(selection)
        {
            case 1:
                doPlayerAttack();
                break;
            case 2:
                doPlayerHeal();
                break;
            case 3:
                doPlayerEquip();
                break;
            case 4:
                doPlayerFlee();
        }
        if(selection > 4 || selection == 0) playerTurn();
    }

    private static void enemyTurn()
    {
        int selection = 0;
        if(cur.getHealth() == cur.getMaxHealth()) selection = r.nextInt(2);
        else selection = r.nextInt(3);

        switch(selection)
        {
            case 0:
                doEnemyAttackOne();
                break;
            case 1:
                doEnemyAttackTwo();
                break;
            case 2:
                doEnemyHeal();
                break;
        }
        if(player.getHealth() <= 0) 
        {
            state = $GameState.BATTLE_LOSS;
            System.out.println("YOU WERE BESTED BY YOUR ENEMY... COME ADVENTURE AGAIN!");
        }
    }

    //#region Option Methods

    private static void doPlayerAttack()
    {
        int i = player.getDamageRating();
        cur.takeDamage(i);
    }

    private static void doPlayerHeal()
    {
        int healResult = player.heal();
        if(healResult == 0) return;
        if(healResult == -1) System.out.println("NO CONSUMABLE ITEMS :(");
        else if(healResult == 1) System.out.println("ALREADY AT MAX HEALTH");
        playerTurn();
    }

    private static void doPlayerEquip()
    {
        boolean isEquipped = player.equip();
        if(isEquipped) return;
        System.out.println("YOUR INVENTORY IS EMPTY");
        playerTurn();
    }

    private static void doPlayerFlee()
    {
        boolean fled = player.tryToFlee();
        if(!fled) System.out.println("YOU WEREN'T FAST ENOUGH TO FLEE!");
        else
        {
            System.out.println("SUCCESS! YOU FLED!");
            state = $GameState.DUNGEON_ENTER;
        }
    }

    //================= ENEMY METHODS =================//

    private static void doEnemyAttackOne()
    {
        player.changeHealth(-cur.primaryAttack());
    }

    private static void doEnemyAttackTwo()
    {
        player.changeHealth(-cur.secondaryAttack());
    }

    private static void doEnemyHeal()
    {
        cur.heal();
    }

    //#endregion

    //#region Management Methods

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

    public static Scanner getKeyBoard()
    {
        return kb;
    }

    public static void waitTime(int millis)
    {
        try{
            Thread.sleep(millis);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

    //#endregion
}