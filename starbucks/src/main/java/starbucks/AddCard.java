
package starbucks;

/**
 * Add New Card Screen
 */
public class AddCard extends Screen
{ 
    private AddCardNumber adcn;
    private AddCardCode adcd;
    private AddCardKeyPad akp;
    private AddCardDisplay acd ;
    
    public AddCard()
    {
        adcn = new AddCardNumber();
        adcd = new AddCardCode();
        akp = new AddCardKeyPad();
        acd = new AddCardDisplay();
   
        
        // setup the composite pattern
           acd.addcardscreenSubComponent( adcn ) ;
           acd.addcardscreenSubComponent( adcd) ;
           acd.addcardscreenSubComponent( akp );
        //ac.addSubComponent( kp ) ;
        
    }

    /**
     * Get Display Contents
     * @return Display Contents
     */
    public String display()
    {
        String output = " ";
        output = acd.addCardScreendisplay() ;
        
        return output;
    }
    /**
     * Send Previous Screen - Not Used
     * @param x X coordinate
     * @param y Y Coordinate
     */
    @Override
    public void touch(int x,int y){

             acd.cardtouch( x , y);
    }
    /** 
    *Previous Screen - Navigates to add card number and add card code
    * Initializes the cardIDnumber and card code to 0 and navigates to settings
    **/    
    @Override
        public void prev() {
            adcn.previousScreen() ;
    }
    /**
     * Set Next Screen - Not Used 
     */   
    @Override
    public void next() {
        adcn.nextScreen() ;
    }
}
