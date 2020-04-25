package starbucks;

/**
 * Base Class for My Cards.
 * 
 * Provides Common Functionality
 * For Setting Up the Composite and 
 * Chain of Responsibility Patterns.
 * 
 */
public class MyCards extends Screen implements IUpdateMyCardsObserver
{
    private static double cardbalance;
    private double finalbalance;
    public MyCards()
    {
       setcardbalance(0);
       //cardnumber="[000000000]";
       finalbalance = 0.00;

    }
    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setcardbalance(double value) {
      cardbalance = value;
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    @Override
    public void touch(int x,int y){
    
    if(x==3 & y==3)
    {
        AppController.frame.cmd("F");
    }
    else if(x==2 & y==4)
    {
        AppController.frame.cmd("G");
    }
        
    }
    /**
     * Send MyCardsUpdate event
    * @param CardID card number
    * @param Balance card balance
    */ 
   public void myCardsUpdate(double Balance)
   {
       setcardbalance(Balance);
   }
    /**
     * Get Display Contents
     * @return Display Contents
    */ 
   @Override
   public String display(){
       finalbalance = cardbalance;
    return "$"+String.format("%.2f",finalbalance);
    }
}
