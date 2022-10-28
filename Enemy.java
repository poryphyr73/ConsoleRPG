public abstract class Enemy 
{
    private int maxHealth, health, expOnKill, attackDamage, rawDefense;
    private int itemRarity;
    private Item[] potentialDrops;

    //enchanted hat (no AC, ench), rusty iron sword, curse of binding armor, 

    public Enemy(int maxHealth, int expOnKill, int attackDamage, int rawDefense, int itemRarity)
    {
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.expOnKill = expOnKill;
        this.attackDamage = attackDamage;
        this.rawDefense = rawDefense;
        this.itemRarity = itemRarity;
    }

    public void reset()
    {
        health = maxHealth;
    }

    //#region Getters

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getHealth()
    {
        return health;
    }

    public int getExpOnKill()
    {
        return expOnKill;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public int getRawDefense()
    {
        return rawDefense;
    }

    //#endregion

    //#region Gameplay Methods

    public abstract int primaryAttack();

    public abstract int secondaryAttack();

    public abstract String getIcon();

    public abstract void takeDamage(int k);

    public abstract void heal();

    public void changeHealth(int k)
    {
        health += k;
        if(health <= 0) Manager.setNewState($GameState.BATTLE_WIN);
        if(health >= maxHealth) health = maxHealth;
    }

    //#endregion
}
