package starbucks;


/**
 * Display IAddCardDisplayComponent here.
 */
public interface IAddCardDisplayComponent
{
    /**
     * Return Display Component Contents
     * @return Display Component Contents
     */
    public String addCardScreendisplay() ;

    /**
     * Add A Child Component
     * @param c Child Component
     */
    public void addcardscreenSubComponent( IAddCardDisplayComponent c ) ;
    
}
