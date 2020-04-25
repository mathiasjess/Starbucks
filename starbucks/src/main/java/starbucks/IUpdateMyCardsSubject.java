package starbucks;

/** Key Pad Subject Interface */
public interface IUpdateMyCardsSubject
{
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach( IUpdateMyCardsObserver obj ) ;

    /**
     * Remove Observer from Subscription
     * @param obj Observer Object
     */
    void removeObserver( IUpdateMyCardsObserver obj ) ;

    /**
     * Trigger Events to Observers
     */
    void notifyObservers() ;
}
