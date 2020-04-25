package starbucks;

/**
 * Base interface for IScreen
 */
public interface IScreenBase {
	
	/**
     * Send touch events to screen
     * @param x Touch X
     * @param y Touch Y
     */
    void touch(int x, int y) ;              

    /**
     * Displays screen components
     * @return Return Screen Contents
     */
    String display() ;       

    /**
     * Returns name of screen
     * @return Screen Name
     */
    String name() ;                    

}
