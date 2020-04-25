package starbucks;

/** Touch Event Handlers for Add Card */
public interface ITouchAddCardEventHandler
{

    /**
     * Touch Event at X and Y
     * @param x X Coord
     * @param y Y Coord
     */
    void cardtouch(int x, int y);

    /**
     * Set Next Handler in Event Chain
     * @param next Next Handler Object
     */
    void setNextcarddigit( ITouchAddCardEventHandler next) ;
}
