package starbucks;

/**
 * Card ID validity Observer Interface
 */
public interface ICardCodeValidityObserver
{
    /**
     * Key Event to Notify Observers 
     * @param Codedigits Number of Digits So Far
     * @param CardCode    Key/Digit Pressed
     */
    void cardCodeUpdate( int Codedigits) ;
}
