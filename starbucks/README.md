# Starbucks Mobile App (Text UI) Project


## Grader Test Results

Grader results are posted in the following files:

* tests.html
* bugs.html
* codesmells.txt

## Running Local Tests

If you have Gradle 4.9 installed locally, you can run the following Makefile rules to get local results.  Please note that the JUnit tests will be only the ones locally available and not the full set of tests the Grader uses.

<pre>
run:
    make run

test:
    make test

spotbugs:
    make spotbugsMain

codesmells:
    make codesmells
</pre>


##  Implementation of the Add Card and Payments process for Starbucks

For AddCard 3 patterns are used namely:
1. Composite Pattern
2. Chain of responsibility Pattern
3. Observer Pattern

1. Composite Pattern: This pattern is used to display the contents of the AddCard screen. The interface is IAddCardDisplayComponent. The composite pattern consists of 4 classes namely AddCardDisplay, AddCardNumber, AddCardCode and AddCardKeyPad. The composite pattern is set up in the AddCard class. The addcardscreenSubComponent method of the  AddCardDisplay class has the sequence in which the display methods of each of the classes are called. The AddCardnumber class is called first which displays the Card ID, followed by the AddCardCode class which displays the 3 digit Card code and then followed by the AddCardKeyPad class which displays the keypad widget.

2. Chain of responsibility Pattern: The chain of responsibility pattern is used to input the Card ID digits and card code digits. The interface used to implement this pattern is ITouchAddCardEventHandler. 4 classes namely AddCardDisplay, AddCardNumber, AddCardCode and AddCardKeyPad implement this interface. When the user inputs a number using touch coordinates, the flow comes to the current screen which is the AddCard class. The touch method of the AddCard class sends the touch coordinates to the AddCardDisplay. Since the touch coordinates will not be fetched in this class, the next eventhandler sends the touch coordinates to the AddCardNumber class. The AddCardNumber fetches the coordinates and does some checks. If the coordinates correspond to digits 1 - 9, it calls the updatecardnumber() method. The updatecardnumber() method appends the digits until the count reaches 9. Once the count reaches 9, it notifies the observer. If coordinates touch(3,8) is pressed, the backspacecardnumber() method is called which deletes the last number appended .Else If touch(2,3) is pressed, the boolean value focus will be set to true. As long as the focus is set to true, the next event handler is called which is the AddCardCode class. The AddCardCode class fetches the coordinates and does some checks. If the coordinates correspond to digits 1 - 3, it calls the updatecardcode() method. The updatecode() method appends the digits until the count reaches 3. Once the count reaches 3, it notifies the observer. Else if the user enters touch(1,2) or touch(2, 2) or touch(3,2), the focus is set to false. When the focus sets to false, the AddCardNumber will implement the next touch entered.

3. Observer Pattern: There are four interfaces used here which implement the subject observer patter. The AddCardNumber class implements the ICardIDValiditySubject and the MyCardsPay class implements the ICardIDValidityObserver. Once the Card number digits entered = 9, the updatecardnumber() of the AddCardNumber class calls the ICardIDValidityObserver which is the MyCardsPay class. The cardIDUpdate() method of the MyCardsPay class fetches the Card ID and the count of the digits. The AddCardCode class implements the ICardCodeValiditySubject and the MyCardsPay class implements the ICardCodeValidityObserver. Once the Card code digits entered = 3, the updatecardcode() of the AddCardCode class calls the ICardCodeValidityObserver which is the MyCardsPay class. The cardCodeUpdate() method of the MyCardsPay class fetches the Card Code and the count of the digits.

When prev is entered by the user, the prev method() of the AddCardnumber sets the CardID to null, count to 0 and calls the AddCardCode prev method. The prev method() of the AddCode sets the CardCode to null, count to 0 and calls the settings screen

When next is entered by the user, if the Card ID count is not 9, the next method() of the AddCardnumber sets the CardID to null, count to 0 and calls the AddCardCode next method. The next method() of the AddCode sets the CardCode to null, count to 0 and calls the MyCards screen which will display a balance of 0. 

When next is entered by the user, if the Card ID count is 9, the next method() of the AddCardnumber calls the AddCardCode next method and then sets the CardID to null, count to 0 . The next method() of the AddCode calls the MyCardsPay screen. If both the Card ID and card Code digits are 9 and 3 respectively, the MyCardsPay class updates the MyCards class with the new method and the next method in the MyCardsPay calls the MyCards screen. Hence the MyCards screen is displayed with a default balance of $20.00 



The Payments Process implements the Observer pattern

The AddCardNumber and the AddCardCode implements the observer pattern to update the methods in the MyCardsPay class. Now the MyCardsPay executes another observer pattern by implementing IUpdateMyCardsSubject Interface. The MyCards class implements the IUpdateMyCardsObserver interface. Once the Card ID and the Card Code are validates in the MyCardsPay class, the MyCardsPay class notifies the observer(MyCards class) by calling the observer method namely myCardsUpdate method and passes a default balance of $20 to the MyCards class.  

If the user navigates to the MyCardsPay screen and inputs touch(2,2) or touch(3,2), the MyCardsPay substracts the standard transaction of $1.50 from the default balance and notifies the observer MyCards of the new balance by calling the method myCardsUpdate. The MyCards screen then reflects the new balance. If the balance is less than $1.50 , the payments method doesn't execute 