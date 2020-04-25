package starbucks;

/**
 * Pin State Machine Base 
 */
public interface IPinStateMachineBase {

	 /** Set Next State to No Pin State */
    void setStateNoPinDigits() ;

    /** 
     * Set Next State to One Pin state
     * @param digit Pin So Far
     */
    void setStateOnePinDigit( String digit ) ;

    /** 
     * Set Next State to Two Pin state
     * @param digit Pin So Far
     */    
    void setStateTwoPinDigits( String digit ) ;

    /** 
     * Set Next State to Three Pin state
     * @param digit Pin So Far
     */    
    void setStateThreePinDigits( String digit ) ;

    /** 
     * Set Next State to Four Pin state
     * @param digit Pin So Far
     */    
    void setStateFourPinDigits( String digit ) ;
}
