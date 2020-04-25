package starbucks;

/** Rewards Screen */
public class Rewards extends Screen
{

    public Rewards()
    {

    }
    /**
     * Get Display Contents
     * @return Display Contents
     */
    @Override
    public String display()
    {
        String output = "" ;
        output+= "Make Every\n";
        output+= "Visit Count";
        
        return output;
    }
}
