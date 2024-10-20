package battleship.board;

public class Cell 
{
    public boolean containsShip;
    //public String ship;
    public boolean discovered;
    public String debug;
    public boolean sankship;

    public Cell ()
    {
        this.containsShip = false;
        this.discovered = false;
        this.sankship = false;
        this.debug = " - ";
        //this.ship = "e";
    }

    public boolean hitCell ()
    {
        this.discovered = true;
        //this.debug = " O ";
        return this.containsShip;
    }

    public String toString ()
    {
        if (this.discovered)
        {
            if (this.containsShip && !this.sankship)
            {
                return " X ";
            }
            else if (this.sankship)
            {
                return " * ";
            }
            else
            {
                return " O ";
            }
        }
        else
        {
            return " - ";
        }
    }
    
}
