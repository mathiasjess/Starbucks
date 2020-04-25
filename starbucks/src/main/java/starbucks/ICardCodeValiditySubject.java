package starbucks;/** Key Pad Subject Interface */
public interface ICardCodeValiditySubject
{
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach( ICardCodeValidityObserver obj ) ;

    /**
     * Remove Observer from Subscription
     * @param obj Observer Object
     */
    void removeObserver( ICardCodeValidityObserver obj ) ;

    /**
     * Trigger Events to Observers
     */
    void notifyObservers() ;
}