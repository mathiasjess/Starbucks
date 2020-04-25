package starbucks;

import java.util.ArrayList;
/**
 * Write a description of class AddCardCode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AddCardCode extends Screen implements IAddCardDisplayComponent, ITouchAddCardEventHandler, ICardCodeValiditySubject
{
    ITouchAddCardEventHandler nextHandler ;
    private ICardCodeValidityObserver observers ;
    private static String lastCardCode;
    private static int countCardCodeDigits ;
    private static String cardcode;
    private static String finalcardcode;

    /**
     * Constructor for objects of class AddCardCode
     */
    public AddCardCode()
    {
       observers = new MyCardsPay();
       setLastCardCode("");
       setcountCardCodeDigits(0) ;
       setcardcode("");
       setfinalcardcode("");

    }
    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setLastCardCode(String value)
    {
      lastCardCode = value;
    }

    /**
     * Constructor for objects of class AddCardCode
     *@param c [description]
     */
    private static void setcountCardCodeDigits(int c)
    {
      countCardCodeDigits = c;
    }

    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setcardcode(String value)
    {
      cardcode = value;
    }
    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setfinalcardcode (String value)
    {
      finalcardcode  = value;
    }
     /**
     * Get the appended card code
     * @return Display the appended card code
     */ 
   public String addCardScreendisplay()
    {    
        if( countCardCodeDigits == 0)
            {    
                setfinalcardcode("[" + cardcode + "]\n");
                //finalcardcode = "[" + cardcode + "]\n";
                return finalcardcode;
            }
        else
        return finalcardcode;
    }
    /**
     * Add sub component to display AddCard screen
     * @param  c [description]
     */  
    public void addcardscreenSubComponent( IAddCardDisplayComponent c )
    {
        // Does nothing
    }
    
    /**
     * Get Key Number from (X,Y) Touch Coord's
     * @param  x [description]
     * @param  y [description]
     */    
    public void cardtouch(int x, int y)
    {
        if ( y > 4 )
        {
            System.err.println( "KeyPad Touched at (" + x + ", " + y + ")" ) ; 
            setLastCardCode(getCardCode( x, y ));
            if ( x==3 && y==8 )
            {   
                setcountCardCodeDigits(countCardCodeDigits - 1);
                //countCardCodeDigits-- ;
                backspacecardcode();
            }
            else 
               if ((countCardCodeDigits < 3) &&( y < 8 || (x==2 && y==8)) )
                  {
                  setcountCardCodeDigits(countCardCodeDigits + 1);
                  //countCardCodeDigits++ ;
                  updatecardcode();
                  }
            
        }
        else
        {
            if (nextHandler != null)
            {
                nextHandler.cardtouch(x,y) ;
            }
            else
            {
                    /* Control remains in the current page */
            }
        }
    }
    
    
    public void setNextcarddigit( ITouchAddCardEventHandler next)
    {
        nextHandler = next;
    }
     /**
     * Get the last card code digit entered
     * @return Display the last digit of code
     */   
    public String lastCardCode() { 
        System.err.println( "Key Pressed: " + this.lastCardCode) ;
        return this.lastCardCode ; 
    }
    
    /**
     * Get Key Number from (X,Y) Touch Coord's
     * @param  x [description]
     * @param  y [description]
     * @return   [description]
     */
    private String getCardCode( int x, int y )
    {
        int kx = 0, ky = 0 ;
        kx = x;
        ky = y-4 ;
       
        String result = Integer.toString(kx+3*(ky-1));
        if ( kx==3 && ky ==4 ) {
            result = "X";
        }
            //return "X" ;
         if ( kx==2 && ky == 4 ) {
             result = "0";
         }
            //return "0" ;
         if ( kx==1 && ky ==4 ) {
            result = " ";
         }
          return result; 
    }

    /**
    kx = 1, ky = 1  ==> 1
    kx = 1, ky = 2  ==> 4
    kx = 1, ky = 3  ==> 7

    kx = 2, ky = 1  ==> 2
    kx = 2, ky = 2  ==> 5
    kx = 2, ky = 3  ==> 8

    kx = 3, ky = 1  ==> 3
    kx = 3, ky = 2  ==> 6
    kx = 3, ky = 3  ==> 9

    n = kx + 3 * (ky-1)
    */
    
    /**
    * Delete the entered card code
    * Navigate to the settings screen
    */   
   private void backspacecardcode()
   {
       if (cardcode.length() > 0 )
       {
            cardcode = cardcode.substring(0, cardcode.length()-1);
            finalcardcode = "[" + cardcode + "]\n";
       }
       else
       {
            cardcode = "";
            finalcardcode = "[" + cardcode + "]\n";
       }  
   }
    /**
     * Appendthe entered card code
     * Notify my cards Pay
     */  
   private void updatecardcode()
   {   
            cardcode =  cardcode + lastCardCode ;
            finalcardcode = "[" + cardcode + "]" ;
            if (countCardCodeDigits == 3)
            {
                notifyObservers();
            }
        
    }
       /**
     * Attach Key Pad Observer
     * @param obj Observers
     */ 
    public void attach( ICardCodeValidityObserver obj ) 
    {
        this.observers = obj;
    }

    /**
     * Remove Key Pad Observer
     * @param obj Observer
     */
    public void removeObserver( ICardCodeValidityObserver obj )
    {
        this.observers = null;
    }

    /**
     * Notify all Observers of Update Event
     */
    public void notifyObservers( )
    {
        if(this.observers!=null)
        {
            
            this.observers.cardCodeUpdate( countCardCodeDigits) ;
        }
    }
    /**
     * Delete the entered card code
     * Navigate to the settings screen
     */
    public void previousScreen()
    {
        setcardcode("");
        setfinalcardcode("");
        setcountCardCodeDigits(0);
        AppController.frame.cmd("E");  
    }
    /**
     * Check if the card code entered is 3
     * Navigate to the My Cards Screen
     */
    public void nextScreen()
    {
        if(countCardCodeDigits == 3)
        {
           AppController.frame.cmd("A"); 
            setcardcode("");
            setfinalcardcode("");
            setcountCardCodeDigits(0);
        }
        else
        {
            setcardcode("");
            setfinalcardcode("");
            setcountCardCodeDigits(0);
            AppController.frame.cmd("A"); 
        }
    }
    
}
