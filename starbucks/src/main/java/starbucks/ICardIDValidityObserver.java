package starbucks;

/**
 * Card ID validity Observer Interface
 */
public interface ICardIDValidityObserver
{
    /**
     * Key Event to Notify Observers 
     * @param IDdigits Number of Digits So Far
     * @param CardId     Key/Digit Pressed
     */
    void cardIDUpdate( int IDdigits, String CardId ) ;
}
