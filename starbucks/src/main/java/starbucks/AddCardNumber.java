package starbucks;

import java.util.ArrayList;
/**
 * Write a description of class AddCardNumber here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AddCardNumber extends Screen implements IAddCardDisplayComponent, ITouchAddCardEventHandler,ICardIDValiditySubject
{
    
    ITouchAddCardEventHandler nextHandler ;
    private ICardIDValidityObserver observers ;
    private int cardnumbermaxdigits = 9;
    private int countCardDigits;
    private boolean focus;
    String lastCardNumber;
    String cardnumber;
    String finalcardnumber;
    private AddCardCode acc;
     
    public AddCardNumber()
    {
        acc = new AddCardCode();
        observers = new MyCardsPay();
        countCardDigits = 0;
        lastCardNumber = "" ;
        focus = false ;
        cardnumber = "";
        finalcardnumber = "";
    }
    /**
     * Get Display Contents
     * @return Display Contents
     */
    public String addCardScreendisplay()
    {   
        if( countCardDigits == 0)
        {
          finalcardnumber = "[" + cardnumber + "]";
          return finalcardnumber;
        }
        else
        return finalcardnumber;
    }
    /**
     * Add Display Component to Screen
     * @param c Display Component
     */
    public void addcardscreenSubComponent( IAddCardDisplayComponent c )
    {
        // put your code here
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void cardtouch(int x, int y)
    {   
        if ( y > 4 && focus == false)
        {
            System.err.println( "KeyPad Touched at (" + x + ", " + y + ")" ) ; 
            this.lastCardNumber = getCardNumber( x, y ) ;
            if ( x==3 && y==8   )
            {
                countCardDigits-- ;
                backspacecardnumber();
            }
            else if ((countCardDigits < cardnumbermaxdigits) &&( y < 8 || (x==2 && y==8)))
            {
                countCardDigits++ ;
                updatecardnumber();
            }
            else 
            {
              //Do nothing
            }
        }
        else if((x==2 && y==3))
        {
            focus = true;
        } 
        else if (( x ==1 & y==2)||( x ==2 & y==2) || ( x ==3 & y==2))
        {
           focus = false;
        }
        else if ((focus == true)||(countCardDigits == cardnumbermaxdigits))
        {
          nextHandler.cardtouch(x,y) ;  
        }

    }
    
    /**
     * If focus = true, set chain to add card code class
     * @param next Cardevent Handler
     */
    public void setNextcarddigit( ITouchAddCardEventHandler next)
    {
        nextHandler = next;
    }
    /**
     * Fetch value of the last added card number
     * @return  Display last card number
     */
    public String lastCardNumber() { 
        System.err.println( "Key Pressed: " + this.lastCardNumber ) ;
        return this.lastCardNumber ; 
    }
    
    /**
     * Get Key Number from (X,Y) Touch Coord's
     * @param  x [description]
     * @param  y [description]
     * @return   [description]
     */
    private String getCardNumber( int x, int y )
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

    /*
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
   private void backspacecardnumber()
   {
        if (cardnumber.length() > 0 )
        {
            cardnumber = cardnumber.substring(0, cardnumber.length()-1);
            finalcardnumber = "[" + cardnumber + "]";
        }
        else
        {
            cardnumber = "";
            finalcardnumber = "[" + cardnumber + "]";
        }
   }
    /**
     * Appendthe entered card code
     * Notify my cards Pay
     */ 
   private void updatecardnumber()
   { 

            cardnumber = cardnumber + lastCardNumber;

            finalcardnumber = "[" + cardnumber + "]";
            
            if (countCardDigits == cardnumbermaxdigits)
            {
                notifyObservers();
            }
       
    }
        /**
     * Attach Key Pad Observer
     * @param obj Observer
     */
    public void attach( ICardIDValidityObserver obj ) 
    {
        this.observers= obj  ;
    }

    /**
     * Remove Key Pad Observer
     * @param obj Observer
     */
    public void removeObserver( ICardIDValidityObserver obj )
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
            this.observers.cardIDUpdate( countCardDigits, finalcardnumber ) ;
        }
    }
    /**
     * Delete the entered card number
     * Navigate to the settings screen
     */
    public void previousScreen()
    {
        cardnumber ="";
        finalcardnumber = "";
        countCardDigits = 0;
        acc.previousScreen();   
    }
    /**
     * Check if the card number entered is 9
     * Navigate to the My Cards Screen
     */
    public void nextScreen()
    {
        if(countCardDigits == cardnumbermaxdigits)
        {
           acc.nextScreen(); 
            cardnumber ="";
            finalcardnumber = "";
            countCardDigits = 0;
        }
        else
        {
            cardnumber ="";
            finalcardnumber = "";
            countCardDigits = 0;
            acc.nextScreen();
    }
    }
}
