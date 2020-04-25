package starbucks;/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
   
    public MyCardsOptions()
    {
       
    }
    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    @Override
    public void touch(int x,int y){
    
    if((x==1 & y==7) ||(x==2 & y==7) || (x==3 & y==7))
    {
        AppController.frame.cmd("H");
    }
        
    }
    /**
     * Get Display Contents
     * @return Display Contents
     */
    @Override
    public String display()
    {
        String output = "" ;
        output+= "Reload\n";
        output+= "Refresh\n";
        output+= "More Options\n";
        output+= "Cancel";
        
        return output;
    }
   
}
