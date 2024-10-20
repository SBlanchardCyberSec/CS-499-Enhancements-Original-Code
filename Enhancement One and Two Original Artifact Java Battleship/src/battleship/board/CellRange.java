package battleship.board;

import java.util.ArrayList;
import java.awt.Point;

public class CellRange 
{
    public int startx;
    public int starty;
    public int length;
    public boolean horizontal;
    //public ArrayList<int []> tcoords;
    public ArrayList<Point> coords;
    public String ship;


    public CellRange (int x, int y, int l, boolean h, String ship) throws IllegalArgumentException
    {

        if ((x < 0 || x > 29) || (y < 0 || y > 29))
        {
            //System.out.println("Cell Range x or y out of bounds");
            throw new IllegalArgumentException("Cannot create CellRange with start coords %dx%d".formatted(x, y));
        }

        else if (l < 1)
        {
            throw new IllegalArgumentException("Cannot create CellRange with length %d".formatted(l));
        }

        else if (((x + l) > 29) || ((y + l) > 29))
        {
            throw new IllegalArgumentException("Cannot create CellRange with start coords %dx%d and length %d due to out of bounds".formatted(x, y, l));
        }

        else
        {
            this.startx = x;
            this.starty = y;
            this.coords = new ArrayList<Point>();
            Point temp;
            for (int i = 0; i < l; i++)
            {
                if (h)
                {
                    temp = new Point(x + i, y);
                }
                else
                {
                    temp = new Point(x, y + i);
                }
                this.coords.add(temp);
            }
            
        }

        
        if (ship.equals("c"))
        {
            this.ship = "Carrier";
        }
        else if (ship.equals("b"))
        {
            this.ship = "Battleship";
        }
        else if (ship.equals("c"))
        {
            this.ship = "Destroyer";
        }
        else if (ship.equals("s"))
        {
            this.ship = "Submarine";
        }
        else if (ship.equals("p"))
        {
            this.ship = "Patrol Boat";
        }
        else
        {
            this.ship = "Unknown";
        }

        //this.ship = ship;




    }

    public boolean checkCollision (CellRange z)
    {
        for (Point p: this.coords)
        {
            for (Point p2: z.coords)
            {
                if (p.equals(p2))
                {
                    System.out.println("CellRange Collision at (%d, %d)".formatted(p.x, p.y));
                    return true;
                }
            }
        }
        return false;        
    }
    
}
