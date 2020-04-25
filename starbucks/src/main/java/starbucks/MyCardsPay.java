package starbucks;

/** My Card Pay Screen */
public class MyCardsPay extends Screen implements ICardIDValidityObserver, ICardCodeValidityObserver, IUpdateMyCardsSubject
{

    private IUpdateMyCardsObserver observers ;
    private static int IDnum;
    private static int Codenum;
    private static String CardIDnum ;
    private static double balance;
    private double finalbalance;
    private double transcharge;
    private MyCards mc;
    
    public MyCardsPay()
    {   
         observers = new MyCards();
         setIDnum(0);
         setCodenum(0);
         mc = new MyCards();
         setbalance(20);
         setCardIDnum("[000000000]");
         finalbalance = 0.00;
         transcharge = 1.50;
    }
    
    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setIDnum(int value)
    {
      IDnum = value;
    }

    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setCodenum(int value)
    {
      Codenum = value;
    }

    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setbalance(double value)
    {
      balance = value;
    }

    /**
     * Constructor for objects of class AddCardCode
     *@param value [description]
     */
    private static void setCardIDnum(String value)
    {
      CardIDnum = value;
    }
   
    /** Add card number and card code 
     * Send Previous Screen - Not Used
     * @param x Previous Screen Object
     * @param y Previous Screen Label
     */
         @Override
    public void touch(int x,int y)
    {       if( x ==3 & y==3)
            {
                AppController.frame.cmd("A");
            }
            else if((balance > transcharge) && ( x ==2 & y==2)||( x ==3 & y==2))
            {
                setbalance(balance - transcharge);
                mc.myCardsUpdate(balance);
            }
           
    }
    /**
     * Get Display Contents
     * @return Display Contents
     */
    @Override
    public String display()
    {
        String output = "\n" +CardIDnum;
        output+= "\n\n\n";
        output+= "Scan Now\n";
        
        return output;
    }
    /** Add card number and card code 
     * Send Previous Screen - Not Used
     * @param IDdigits Previous Screen Object
     * @param CardId Previous Screen Label
     */
    public void cardIDUpdate( int IDdigits, String CardId )
    {
        setIDnum(IDdigits);
        setCardIDnum(CardId);
        notifyObservers();
        
        
    }
    /** Add card number and card code 
     * Send Previous Screen - Not Used
     * @param Codedigits Previous Screen Object
     * @param CardCode Previous Screen Label
     */
    public void cardCodeUpdate( int Codedigits)
    {
        setCodenum(Codedigits);
        notifyObservers();
    }
    /** Attach Update card observer
     * @param obj Update card observer
     */
    public void attach( IUpdateMyCardsObserver obj )
    {
        this.observers = obj ;
    }
    /** Remove Update card observer
     * Send Previous Screen - Not Used
     * @param obj Update card observer
     */
    public void removeObserver( IUpdateMyCardsObserver obj )
    {
        this.observers = null;
        
    }
    /** Remove Update card observer*/
    public void notifyObservers()
    {   
        if((IDnum ==9) && (Codenum ==3))
        {
            if(this.observers!=null)
            {   setIDnum(0);
                setCodenum(0);
                finalbalance = balance ;
                observers.myCardsUpdate(finalbalance) ;
            }
        }
    }
 
}

