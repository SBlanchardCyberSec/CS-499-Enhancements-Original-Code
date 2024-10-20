package battleship.ship;

public class Ship 
{
    public String model;
    public int length;
    public int health;
    public boolean alive = true;
    public boolean horizontal;
    //public ArrayList <int[][]> coords;

    public Ship (String m, boolean h)
    {
        if (m.equals("c"))
        {
            this.model = "Carrier";
            this.length = 5;
            this.health = 5;
            this.horizontal = h;            

        }
        else if (m.equals("b"))
        {
            this.model = "Battleship";
            this.length = 4;
            this.health = 4;
            this.horizontal = h;  
        }
        else if (m.equals("d"))
        {
            this.model = "Destroyer";
            this.length = 3;
            this.health = 3;
            this.horizontal = h;  
        }
        else if (m.equals("s"))
        {
            this.model = "Submarine";
            this.length = 3;
            this.health = 3;
            this.horizontal = h;  
        }
        else if (m.equals("p"))
        {
            this.model = "Patrol Boat";
            this.length = 2;
            this.health = 2;
            this.horizontal = h;  
        }
        else 
        {
            this.model = "Unknown";
            this.length = 1;
            this.health = 1;
            this.horizontal = h;
        }

    }

    public void hitShip ()
    {
        this.health--;
        if (this.health <= 0)
        {
            this.alive = false;
        }
    }
    

    public String toString ()
    {
        return this.model;
    }







}
