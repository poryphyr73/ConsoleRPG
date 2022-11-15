public class RealItems
{
    public static final Item[] ITEMS = 
    { 
        //#region ARMOR

        new Armor("Enchanted Hat", 0, 100, 1),

        //#endregion

        //#region WEAPONS

        new Weapon("Rusty Sword", 2, 80, 5)
        
        //#endregion

        //#region CONSUMABLES


        
        //#endregion
    };

    //enchanted hat (no AC, ench), rusty iron sword, curse of binding armor, 

    public RealItems() {}

    public static Item[] getItems()
    {
        return ITEMS;
    }
}