package starbucks;


/**
 * Write a description of class AddCardKeyPad here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AddCardKeyPad implements IAddCardDisplayComponent, ITouchAddCardEventHandler
{
    // instance variables - replace the example below with your own
    ITouchAddCardEventHandler nextHandler ;
    /**
     * Constructor for objects of class AddCardKeyPad
     *@return Display contents
     */
   public String addCardScreendisplay()
    {
        //return " [1] [2] [3]\n [4] [5] [6]\n [7] [8] [9]\n [_] [0] [X]"  ;
        
        String output =  "\n [1] [2] [3]\n" ;
               output += " [4] [5] [6]\n" ;
               output += " [7] [8] [9]\n" ;
               output += " [_] [0] [x]" ;

        return output ;
    }
    /**
     * Add Display Component to Add Card Screen
     * @param c Display Component
     */
    public void addcardscreenSubComponent( IAddCardDisplayComponent c )
    {
        //No code here
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void cardtouch(int x, int y)
    {
        if (nextHandler != null)
        {
            nextHandler.cardtouch(x,y) ;
        }
    }
    
    /**
     * Do nothing
     * @param next Cardevent Handler
     */
    public void setNextcarddigit( ITouchAddCardEventHandler next)
    {
        nextHandler = next;
    }
}
