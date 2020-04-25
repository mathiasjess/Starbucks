package starbucks;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {

    private IScreen mycards ;
    private IScreen store ;
    private IScreen rewards ;
    private IScreen payments ;
    private IScreen settings;
    private IScreen mycardspay ;
    private IScreen mycardsoptions ;
    private IScreen mycardsmoreoptions ;
    private IScreen addcard ;
    
    
    private IMenuCommand displayMyCards ;
    private IMenuCommand displayPayments ;
    private IMenuCommand displayRewards ;
    private IMenuCommand doStore ;
    private IMenuCommand displaySettings ;
    private IMenuCommand displaymycardspay;
    private IMenuCommand displaymycardsoptions;
    private IMenuCommand displaymycardsmoreoptions;
    private IMenuCommand displayaddcard;
    
    
    public static IFrame frame ;

    public AppController() {
        mycards = new MyCards() ;
        store = new Store() ;
        rewards = new Rewards() ;
        payments = new Payments() ;
        settings = new Settings();
        mycardspay = new MyCardsPay();
        mycardsoptions = new MyCardsOptions();
        mycardsmoreoptions = new MyCardsMoreOptions();
        addcard = new AddCard();
        frame = new Frame( mycards ) ;

        // setup command pattern
        displayMyCards  = new MenuCommand() ;
        displayPayments = new MenuCommand() ;
        displayRewards  = new MenuCommand() ;
        doStore         = new MenuCommand() ;
        displaySettings = new MenuCommand() ;
        displaymycardspay = new MenuCommand() ;
        displaymycardsoptions = new MenuCommand() ;
        displaymycardsmoreoptions= new MenuCommand() ;
        displayaddcard = new MenuCommand();
        
        displayMyCards.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( mycards ) ;
              }
        }
        ) ;
        displayPayments.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( payments ) ;
              }
        }
        ) ;
        displayRewards.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( rewards ) ;
              }
        }
        ) ;
        doStore.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( store ) ;
              }
        }
        ) ;
        displaySettings.setReceiver(
          new IMenuReceiver() {
             /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( settings ) ;
              }
        }
        ) ;
       displaymycardspay.setReceiver(
          new IMenuReceiver() {
             /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( mycardspay) ;
              }
        }
        ) ;
       displaymycardsoptions.setReceiver(
          new IMenuReceiver() {
             /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( mycardsoptions ) ;
              }
        }
        ) ;
        displaymycardsmoreoptions.setReceiver(
          new IMenuReceiver() {
             /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( mycardsmoreoptions ) ;
              }
        }
        ) ;
        displayaddcard.setReceiver(
          new IMenuReceiver() {
             /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( addcard ) ;
              }
        }
        ) ;
        
        frame.setMenuItem ( "A", displayMyCards ) ;
        frame.setMenuItem ( "B", displayPayments ) ;
        frame.setMenuItem ( "C", displayRewards ) ;
        frame.setMenuItem ( "D", doStore ) ;
        frame.setMenuItem ( "E", displaySettings ) ;
        frame.setMenuItem ( "F", displaymycardspay ) ;
        frame.setMenuItem ( "G", displaymycardsoptions) ;
        frame.setMenuItem ( "H", displaymycardsmoreoptions ) ;
        frame.setMenuItem ( "I", displayaddcard ) ;

    }


    /**
      * Switch to Landscape Mode
      */
    public void landscape() {
        frame.landscape() ;
    }

    /**
     * Switch to Portait Mode
     */
    public void portrait() {
        frame.portrait() ;
    }

    /**
     * Send In Touch Events
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
        frame.touch(x, y) ;
    }

    /**
     * Display Current Screen
     */
    public void display() {
        frame.display() ;
    }

    /**
     * Execute Menu Bar Command
     * @param c Menu Bar Option (A, B, C, D ,E, F, G, H or I)
     */
    public void execute( String c ) {
        frame.cmd( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        frame.previousScreen() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        frame.nextScreen() ;
    }

    /**
     * Get Current Screen Name
     * @return Screen Name
     */
    public String screen() {
        return frame.screen() ;
    }

    /**
     * Get Current Screen Contents
     * @return Current Screen Contents
     */
    public String screenContents() {
        return frame.contents() ;
    }

}
