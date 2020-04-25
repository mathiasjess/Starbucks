package starbucks;

import java.util.* ;

/** Settings Screen */
public class Settings extends Screen //implements IDisplayComponent
{
    public Settings()
    {

    }

    /**
     * Get Display Contents
     * @return Display Contents
     */
    public String display()
    {
    String output = "";
    output += "Add Card\n"; 
    output += "Delete Card\n"; 
    output += "Billing\n"; 
    output += "Passcode\n"; 
    output += "About|Terms\n"; 
    output += "Help\n"; 
    
    return output;
    }
    
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    @Override
    public void touch(int x,int y)
    {
    
        if((x==1 & y==1) ||(x==2 & y==1) || (x==3 & y==1) )
        {
            AppController.frame.cmd("I");
        }
    }
   
}
