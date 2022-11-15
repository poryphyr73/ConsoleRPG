public abstract class Item 
{
    private boolean[] TYPE_BONUS = new boolean[5]; //INDEX: 0 = fire, 1 = freeze, 2 = electricity, 3 = poison, 4 = wind
    private static final String[] TYPE_BONUS_TOSTRING = {"fire", "freeze", "electricity", "poison", "wind"};

    public Item(int typesBin)
    {
        for(int i = 4; i >= 0; i--)
        {
            boolean bin = typesBin >= Math.pow(2, i);
            TYPE_BONUS[i] = bin;
            int binToInt = (bin) ? 1 : 0;
            typesBin -= Math.pow(2, i) * binToInt;
        }
    }
    public Item(){}

    public String toString()
    {
        String ret = "";
        for(int i = 0; i < TYPE_BONUS.length; i++)
        {
            if(TYPE_BONUS[i]) ret += TYPE_BONUS_TOSTRING[i];
            if(i < TYPE_BONUS.length - 1) ret += ", ";
        }
        if(ret.equals("")) ret += "nothing";
        return ret;
    }
}
