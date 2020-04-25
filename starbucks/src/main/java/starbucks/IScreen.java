/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Screen Interface */
public interface IScreen extends IScreenBase
{     

    /**
     * Navigate to next screen
     */
    void next() ;                           

    /**
     * Navigate to previous screen
     */
    void prev() ;      

    /**
     * Set next screen with action name
     * @param s Screen
     * @param n Action
     */
    void setNext(IScreen s, String n ) ;    

    /**
     * Set previous screen with action name
     * @param s Screen
     * @param n Action
     */
    void setPrev(IScreen s, String n ) ;    

}
