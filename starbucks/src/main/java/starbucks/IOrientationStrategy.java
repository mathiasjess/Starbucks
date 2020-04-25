package starbucks;

/** Orientation Strategy Interface */
public interface IOrientationStrategy
{
    /**
     * Display Screen (with respect to this Strategy)
     * @param s Screen to Display
     */
    void display(IScreen s) ;

    /**
     * Get Screen Contents (instead of display to terminal)
     * @param  s Screen to get Contents
     * @return   Contents of Screen
     */
    String contents(IScreen s) ;

    /** Select A Menu Option */
    void selectA() ;

    /** Select B Menu Option */
    void selectB() ;

    /** Select C Menu Option */
    void selectC() ;

    /** Select D Menu Option */
    void selectD() ;
    
    /** Select E Menu Option */    
    void selectE() ;

    /** Select F Menu Option */    
    void selectF() ;
    
    /** Select G Menu Option */    
    void selectG() ;
    
    /** Select H Menu Option */    
    void selectH() ;
    
    /** Select I Menu Option */    
    void selectI() ;
    
}
