package starbucks;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdditionalTestCases {

    IApp app ;

    @Before
    public void setUp()
    {
        app = new AppAuthProxy() ;
    }

        /**
     * Verify that no * is displayed when invalid coordinates are typed
     * 
     */
    @Test
    public void InvalidKeypadTouchCoordinatesTest()
    {
        assertEquals("PinScreen", app.screen());
        app.touch(1,1) ;
        app.touch(2,1) ;
        app.touch(3,1) ;
        app.touch(3,1) ;
        app.touch(3,1) ;
        app.touch(3,1) ;
        app.touch(1,1) ;
        assertEquals("PinScreen", app.screen());
        String content=app.screenContents();
        String contentSplit[]=content.split("\\r?\\n");
        assertEquals(" [_][_][_][_]", contentSplit[5]);
    }

    
    /**
     * Verify that the Invalid Pin message is displayed on the Pin screen when the login authentication fails
     */
    @Test
    public void InvalidPinTest()
    {
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,5) ;
        assertEquals("PinScreen", app.screen());
        String content=app.screenContents();
        String contentSplit[]=content.split("\\r?\\n");
        assertEquals("  Invalid Pin", contentSplit[3]);
    }

    /**
     * Verify Passcode is set to no state when authentication fails on entering wrong pin
     */
    @Test
    public void PasscodeBlankOutOnInvalidPinTest()
    {
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(1,5) ;
        app.touch(1,5) ;
        app.touch(1,5) ;
        assertEquals("PinScreen", app.screen());
        String content=app.screenContents();
        String contentSplit[]=content.split("\\r?\\n");
        assertEquals(" [_][_][_][_]", contentSplit[5]);
    }
    //Verify the corner case when user tries to add an empty card
    /**
     * Verify the Add Card screen reroutes to
     * itself on click Next button
     */
    @Test
    public void AddEmptyCardTest(){
        String[] lines ;
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        app.touch(1,1) ; // Add New Card
        assertEquals("AddCard", app.screen());
        app.next();//Try to add the card
        lines = app.screenContents().split("\n");
        assertEquals("[]", lines[4].trim());
        assertEquals("[]", lines[5].trim());
        assertEquals("AddCard", app.screen());

    }

    
    /**
     * Verify PinScreen still displays after entering
     * 2 digits and backspace thrice
     */
    @Test
    public void TwoDigitsThriceBackspacePasscodeTest()
    {
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,8) ;
        app.touch(3,8) ;
        app.touch(3,8) ;

        assertEquals("PinScreen", app.screen());
        app.touch(1,5);

        String content=app.screenContents();
        String contentSplit[]=content.split("\\r?\\n");
        assertEquals(" [*][_][_][_]", contentSplit[5]);
    }
    
    /**
     * Verify the focus is on Card Code
     * on touching (2,3)
     */
    @Test
    public void VerifyFocusOnCardCodeWithTouch2and3Test() {
    	String[] lines ;
    	assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        app.touch(1,1) ; // Add New Card
        assertEquals("AddCard", app.screen());
        app.touch(2,3);
        app.touch(1,5); // 1
        app.touch(2,6); // 5
        app.touch(3,7); // 9
        app.touch(3,6); // 6
        app.display() ;
        lines = app.screenContents().split("\n");
        //Only 3 digits getting displayed confirms focus on Card Code
        assertEquals("[159]", lines[5].trim());
    }
    
    /**
     * Verify CardNumber and CardCode blanks out
     * on entering card number less than 9 digits
     */
    @Test
    public void InvalidCardNumberTest() {
        String[] lines ;
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        app.touch(1,1) ; // Add New Card
        assertEquals("AddCard", app.screen());
        app.touch(1,5); // 1
        app.touch(2,5); // 2
        app.touch(3,5); // 3
        app.touch(1,6); // 4
        app.touch(2,6); // 5
        app.touch(3,6); // 6
        app.touch(1,7); // 7
        app.next();//Try to add the card
        lines = app.screenContents().split("\n");
        assertEquals("[]", lines[4].trim());
        assertEquals("[]", lines[5].trim());
        assertEquals("AddCard", app.screen());
    }

    /**
     * Verify CardNumber and CardCode goes back to no state on entering card code less than 3 digits
     * 
     */
    @Test
    public void InvalidCardCodeTest() {
        String[] lines ;
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        app.touch(1,1) ; // Add New Card
        assertEquals("AddCard", app.screen());
        app.touch(1,5); // 1
        app.touch(2,5); // 2
        app.touch(3,5); // 3
        app.touch(1,6); // 4
        app.touch(2,6); // 5
        app.touch(3,6); // 6
        app.touch(1,7); // 7
        app.touch(2,7); // 8
        app.touch(3,7); // 9
        app.touch(2,3); // focus on card code
        // Card Code digits
        app.touch(3,7); // 9
        app.touch(3,7); // 9
        app.next();//Try to add the card
        lines = app.screenContents().split("\n");
        assertEquals("[]", lines[4].trim());
        assertEquals("[]", lines[5].trim());
        assertEquals("AddCard", app.screen());
    }

    /**
     * Verify the navigation goes to Settings screen on clicking next on Add Card screen
     * 
     */
    @Test
    public void VerifyNavigateToSettingsScreenOnCancelTest() {
    	  assertEquals("PinScreen", app.screen());
          app.touch(1,5) ;
          app.touch(2,5) ;
          app.touch(3,5) ;
          app.touch(1,6) ;
          app.execute("E") ; // Settings Page
          assertEquals("Settings", app.screen());
          app.touch(1,1) ; // Add New Card
          assertEquals("AddCard", app.screen());
          app.prev();//Cancel the action
          assertEquals("Settings", app.screen());
          
    }
    
    /**
     * Verify that no charge is deducted from the card if balance is less than $1.50
     * 
     *
     */
    @Test
    public void NoBalanceDeductionOnLessThanOneFiftyTest() {
    	String[] lines ;
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        app.touch(1,1) ; // Add New Card
        assertEquals("AddCard", app.screen());
        // Card Id digits
        app.touch(1,5); 
        app.touch(2,5);
        app.touch(3,5);
        app.touch(1,6);
        app.touch(2,6);
        app.touch(3,6);
        app.touch(1,7);
        app.touch(2,7);
        app.touch(3,7);
        app.touch(2,3); // focus on card code
        // Card Code digits
        app.touch(3,7);
        app.touch(3,7);
        app.touch(3,7);
        // check digit entry
        lines = app.screenContents().split("\n"); 
        assertEquals("[123456789]", lines[4].trim());
        assertEquals("[999]", lines[5].trim());
        // add card - see balance
        app.next() ;    
        assertEquals("MyCards", app.screen());
        lines = app.screenContents().split("\n");  
        assertEquals("$20.00", lines[7].trim());    
        // switch to payment
        app.touch(3,3); 
        lines = app.screenContents().split("\n");  
        assertEquals("[123456789]", lines[6].trim());
        assertEquals("Scan Now", lines[9].trim());  
        // Make Payments
        app.touch(2,2);  // Pay $1.50
        // Pay $1.50 12 times more to ensure balance goes below $1.50
        for (int i = 0; i < 12; i++) {
        	app.touch(2,2);
		}
        app.touch(3,3); // switch to balance
        lines = app.screenContents().split("\n");  
        assertEquals("$0.50", lines[7].trim()); 
        // switch to payment again
        app.touch(3,3); 
        lines = app.screenContents().split("\n");  
        assertEquals("[123456789]", lines[6].trim());
        assertEquals("Scan Now", lines[9].trim());
        app.touch(2,2); //Pay $1.50
        // switch to balance and ensure nothing is deducted as the balance was less than $1.50
        app.touch(3,3); 
        lines = app.screenContents().split("\n");  
        assertEquals("$0.50", lines[7].trim()); 
    }
    
    /**
     * Verify that the payment of $1.50 using either touch coordinate (2,2) or (3,2)
     * 
     */
    @Test
    public void VerifyPaymentActionWithBothTouchCoordinatesTest() {
    	String[] lines ;
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        app.touch(1,1) ; // Add New Card
        assertEquals("AddCard", app.screen());
        // Card Id digits
        app.touch(1,5); 
        app.touch(2,5);
        app.touch(3,5);
        app.touch(1,6);
        app.touch(2,6);
        app.touch(3,6);
        app.touch(1,7);
        app.touch(2,7);
        app.touch(3,7);
        app.touch(2,3); // focus on card code
        // Card Code digits
        app.touch(3,7);
        app.touch(3,7);
        app.touch(3,7);
        // check digit entry
        lines = app.screenContents().split("\n"); 
        assertEquals("[123456789]", lines[4].trim());
        assertEquals("[999]", lines[5].trim());
        // add card - see balance
        app.next() ;    
        assertEquals("MyCards", app.screen());
        lines = app.screenContents().split("\n");  
        assertEquals("$20.00", lines[7].trim());    
        // switch to payment
        app.touch(3,3); 
        lines = app.screenContents().split("\n");  
        assertEquals("[123456789]", lines[6].trim());
        assertEquals("Scan Now", lines[9].trim());  
        // Make Payments
        app.touch(2,2);  // Pay $1.50
        app.touch(3,3); // switch to balance
        lines = app.screenContents().split("\n");  
        assertEquals("$18.50", lines[7].trim()); 
        // switch to payment again
        app.touch(3,3); 
        lines = app.screenContents().split("\n");  
        assertEquals("[123456789]", lines[6].trim());
        assertEquals("Scan Now", lines[9].trim());
        app.touch(3,2); //Pay $1.50
        app.touch(3,3); 
        lines = app.screenContents().split("\n");  
        assertEquals("$17.00", lines[7].trim()); 
    }
    
    /**
     * Verify that the settings screen displays Add Card, Delete Card, Billing, Passcode, About|Terms and Help
     */
    @Test
    public void VerifySettingsDisplayContentTest() {
    	String[] lines ;
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;
        app.touch(2,5) ;
        app.touch(3,5) ;
        app.touch(1,6) ;
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
        lines = app.screenContents().split("\n");
        assertEquals("Add Card", lines[4].trim());
        assertEquals("Delete Card", lines[5].trim());
        assertEquals("Billing", lines[6].trim());
        assertEquals("Passcode", lines[7].trim());
        assertEquals("About|Terms", lines[9].trim());
        assertEquals("Help", lines[10].trim());
    }

    /**
     * Verify the Screen titles on each screen
     */
    @Test
    public void MenuBarItemTest() {
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;  // 1
        app.touch(2,5) ;  // 2
        app.touch(3,5) ;  // 3
        app.touch(1,6) ;  // 4
        assertEquals("MyCards", app.screen());
        app.execute("B") ;
        assertEquals("Payments", app.screen());
        app.execute("C");
        assertEquals("Rewards", app.screen());
        app.execute("D");
        assertEquals("Store", app.screen());
        app.execute("E");
        assertEquals("Settings", app.screen());
        app.execute("A");
        assertEquals("MyCards", app.screen());
    }

    @After
    public void tearDown()
    {
    }

}
