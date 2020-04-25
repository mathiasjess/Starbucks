package starbucks ;

import java.io.Console;
import java.util.Arrays;

/**
 * Main Entry Point.
 */
final class Main {

    /**
     * Prevent Construction.
     */
    private Main() {
        // Utility Class
        return ;
    }
    

    /**
     * Main App Entry Point.
     * @param args No args expected.
     */
    public static void main(final String[] args) {
        System.err.println( "Args: " + Arrays.toString(args)) ;
        IApp app = new AppAuthProxy() ;
        Console c = System.console();
        String msg = "" ;
        for (;;) {
            //System.out.print("\033[H\033[2J") ; // clear the screen
            System.out.flush() ;
            System.out.println(app.screenContents()) ;
            System.out.println( msg ) ;
            System.out.print("=> ") ;
            String ch = c.readLine() ;       // get user command
            String cmd = ch.toLowerCase() ;  // convert to lower case
            cmd = cmd.replaceAll("\\s","") ; // remove all whitespaces          
            msg = processCommands(app, cmd ,msg) ;
               
           
        }
    }
    
    /**
     * handleTouch
     * @param cmd - Commands
     * @param app - Application
     * @return String to display
     */
    private static String handleTouch(String cmd, IApp app) {
    String parms = cmd.replaceFirst("touch", "") ;
    parms = parms.substring(1) ;
    parms = parms.substring(0, parms.length() - 1) ;
    String[] values = parms.split(",") ;
    System.err.println( "Value: " + Arrays.toString(values)) ;
    String x = values[0] ;
    String y = values[1] ;
    
    app.touch( Integer.parseInt(x), Integer.parseInt(y) ) ;
    return "touch: x="+x + " y="+y ; 
    }
    
    /**
     * HandleMenu Selection
     * @param cmd - Command
     * @param app - Application
     * @return String to message
     */
    private static String handleMenuSelection(String cmd, IApp app) {
        String selection = cmd.toUpperCase() ;
       
        app.execute( selection ) ;
        return "selected: " + selection ;
    }
    
    /**
     * login application
     * @param app for app
     */
    private static void login(IApp app) {
        appTouch(app, 1, 5);
        appTouch(app, 2, 5);
        appTouch(app, 3, 5);
        appTouch(app, 1, 6);
    }
    
    /**
     * App Touch Method
     * @param app - Application
     * @param x - x coord
     * @param y - y coord
     */
    private static void appTouch(IApp app, int x , int y) {
        app.touch(x,y);
    }
    
    /**
     * Process Commands
     * @param app - Application
     * @param cmd - Command
     * @param msg - message
     * @return String message
     */
    private static String processCommands(IApp app, String cmd, String msg) {
    /* process commands */
   
    boolean validCommandABC =  cmd.equals("a") || cmd.equals("b") 
            || cmd.equals("c");
    boolean validCommandDE = cmd.equals("d")
            || cmd.equals("e");
    msg = cmd;
    if ( cmd.startsWith("touch") ) {
        msg = handleTouch(cmd, app);
    }  if ( validCommandABC || validCommandDE) {
        msg = handleMenuSelection(cmd, app);
    } 
    if ( cmd.startsWith("prev") ) {
       msg = handleprevious(msg,app);
    }  if ( cmd.startsWith("next") ) {
       msg = handlenext(msg, app);
    }  if (cmd.equalsIgnoreCase( "portrait" )) {
        handleportrait(msg, app);
       
    }  if (cmd.equalsIgnoreCase( "landscape" )) {
        app.landscape() ;
       
    }  if ( cmd.startsWith("login") ) {
        login(app);
        
    }  
    return msg;
    }

/**
 * handle previous screen
 * @param msg - command
 * @param app - IApp
 * @return String - message
 */
private static String handleprevious (String msg, IApp app) {
     msg = "cmd: previous" ;
     app.prev() ;
     return msg;
}

/**
 * handle next screen
 * @param msg - command
 * @param app - IApp
 * @return String - message
 */
private static String handlenext (String msg, IApp app) {
    msg = "cmd: next" ;
    app.next() ;
    return msg;
}

/**
 * handle portrait screen
 * @param msg - command
 * @param app - IApp
 */
private static void handleportrait (String msg, IApp app) {
     app.portrait() ;
}
}

