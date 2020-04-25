package starbucks;


/** Key Pad Subject Interface */
public interface ICardIDValiditySubject
{
    /**
     * Add Observer to Subscribers List
     * @param obj Observer Object
     */
    void attach( ICardIDValidityObserver obj ) ;

    /**
     * Remove Observer from Subscription
     * @param obj Observer Object
     */
    void removeObserver( ICardIDValidityObserver obj ) ;

    /**
     * Trigger Events to Observers
     */
    void notifyObservers() ;
}
