package starbucks;


/**
 * Card ID validity Observer Interface
 */
public interface IUpdateMyCardsObserver
{
    /**
     * Key Event to Notify Observers 
     * @param CardID Number of Digits So Far
     * @param Balance    Key/Digit Pressed
     */
    void myCardsUpdate(double Balance) ;
}
