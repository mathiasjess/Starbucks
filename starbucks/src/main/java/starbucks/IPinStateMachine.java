/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Pin State Machine Interface */
public interface IPinStateMachine extends IPinStateMachineBase
{
    /** Backspace Event */
    void backspace() ;

    /**
     * Number/Key Pressed Event
     * @param digit Key
     */
    void number( String digit ) ;

    /** Valid Pin Event */
    void validPin() ;

    /** Invalid Pin Event */
    void invalidPin() ;
}

