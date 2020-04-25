

package starbucks;

/** Store Screen */
public class Store extends Screen
{

    public Store()
    {

    }
    /** Store Screen 
    * @return the output to be displayed
    */
    @Override
    public String display()
    {
    String output = "";
    output += "X          \n"; 
    output += "      X    \n"; 
    output += "   X       \n"; 
    output += "           \n"; 
    output += "          X\n"; 
    output += "           \n"; 
    
    return output;
    }

}
