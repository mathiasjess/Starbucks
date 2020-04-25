package starbucks;
import java.util.ArrayList;
/** Payments Screen */
public class Payments extends Screen 
{

    public Payments()
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
        output+= "Find Store\n";
        output+= "Enable Payments";
        
        return output;
    }
    
}
