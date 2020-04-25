package starbucks;

import java.util.* ;
/**
 * Write a description of class AddCardDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AddCardDisplay extends Screen implements IAddCardDisplayComponent
{
   /** Display Components */
    private ArrayList<IAddCardDisplayComponent> addcardcomponents = new ArrayList<IAddCardDisplayComponent>() ;
    
        /** Front of Event Chain */
    private ITouchAddCardEventHandler chain ;


    /**
     * Constructor for objects of class AddCardDisplay
     */
    public AddCardDisplay()
    {
        // initialise instance variables
    }
    /**
     * Get Display Add Card screen Contents
     * @return Display Contents
     */
   public String addCardScreendisplay()
    {
        String value = "" ;
        StringBuffer buf = new StringBuffer();
        for (IAddCardDisplayComponent c : addcardcomponents )
        {
            System.err.println( "Screen: " + c.getClass().getName() ) ;
            //value = value + c.addCardScreendisplay() + "\n" ;
            buf.append(c.addCardScreendisplay() + "\n");

        } 
        value = buf.toString();  
        return value ;
    }
        /**
     * Set Composite pattern for add card 
     * @param c Composite pattern object
     */
    public void addcardscreenSubComponent( IAddCardDisplayComponent c )
    {
        addcardcomponents.add( c ) ;
        if (addcardcomponents.size() == 1 )
        {
            chain = (ITouchAddCardEventHandler) c ;
        }
        else
        {
            ITouchAddCardEventHandler prev = (ITouchAddCardEventHandler) addcardcomponents.get(addcardcomponents.size()-2) ;
            prev.setNextcarddigit( (ITouchAddCardEventHandler) c ) ;
        }
    }
    /**
     * Set Next Screen - Not Used 
     * @param x Next Screen Object
     * @param y Next Screen Label
     */
    public void cardtouch(int x, int y)
    {
          chain.cardtouch( x, y);
    }
}
