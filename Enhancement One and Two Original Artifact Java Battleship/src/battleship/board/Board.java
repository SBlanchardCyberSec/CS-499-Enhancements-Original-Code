package battleship.board;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import battleship.ship.Ship;

public class Board
{
    public final int sizeHorizontal;
    public final int sizeVertical;
    public Cell[][] board;
    public int totalhealth = 0;
    public ArrayList<Ship> ships;
    public ArrayList<Point> emptycells;
    public ArrayList<CellRange> filledcells;
    public Random random;

    public CellRange tempcr;
    public Ship tempship;
    public Point tempcoord;


    public Board (int sizeHorizontal, int sizeVertical) throws IllegalArgumentException 
    {
        if ((sizeHorizontal < 5 || sizeHorizontal > 30) || (sizeVertical < 5 || sizeVertical > 30)) 
        {
            throw new IllegalArgumentException("Cannot create Board with size %dx%d".formatted(sizeHorizontal, sizeVertical));

        }

        this.sizeHorizontal = sizeHorizontal;
        this.sizeVertical = sizeVertical;
        this.board = new Cell[this.sizeHorizontal][this.sizeVertical];
        for (int i = 0; i < this.sizeHorizontal; i++)
        {
            for (int ii = 0; ii < this.sizeVertical; ii++)
            {
                this.board[i][ii] = new Cell();
            }
        }
        this.ships = new ArrayList<Ship>();
        this.emptycells = new ArrayList<Point>();
        this.filledcells = new ArrayList<CellRange>();
        for (int i = 0; i < sizeHorizontal; i++)
        {
            for (int ii = 0; ii< sizeVertical; ii++)
            {
                emptycells.add(new Point(i, ii));
            }

        }
        this.random = new Random();

        placeShips();
    }

    public void placeShips ()
    {

        placeShip("c", coinFlip());
        placeShip("b", coinFlip());
        placeShip("d", coinFlip());
        placeShip("s", coinFlip());
        placeShip("p", coinFlip());

    }

    public void placeShip (String type, boolean h)
    {
        ships.add(new Ship(type, h));

        Point temp = randomCoord(ships.getLast().length, h);
        CellRange tempRange = new CellRange(temp.x, temp.y, ships.getLast().length, h, type);
        Boolean flag = false;


        //check collision
        for (CellRange cr: filledcells)
        {
            if (tempRange.checkCollision(cr))
            {
                //collision here
                if (!flag)
                {
                    flag = true;
                    System.out.println("Collision while placing ships!");
                    System.out.println("Currently placed ships (with the offending one last) are: %s".formatted(ships));
                    ships.remove(ships.getLast());
                }
            }
        }

        if (!flag)
        {
            filledcells.add(tempRange);

            this.totalhealth += ships.getLast().health;

            for (Point p: tempRange.coords)
            {
                Cell c = board[p.x][p.y];
                c.containsShip = true;
                c.debug = " %s ".formatted(type.toUpperCase());
                //board[p.x][p.y].ship = type;
            }
        }

        else 
        {
            placeShip(type, h);
        }

    }

    public boolean coinFlip ()
    {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public Point randomCoord (int l, boolean h)
    {
        if (h)
        {
            int randx = random.nextInt(this.sizeHorizontal - l);
            int randy = random.nextInt(this.sizeVertical);

            return new Point(randx, randy);
        }
        else 
        {
            int randx = random.nextInt(this.sizeHorizontal);
            int randy = random.nextInt(this.sizeVertical - l);

            return new Point(randx, randy);
        }
    }

    public void shootCell (Point coord)
    {
        boolean flag = false;
        boolean shipkilled = false;
        int tempint = 0;

        if (this.board[coord.x][coord.y].hitCell())
        {
            for (int i = 0; i < this.filledcells.size(); i++)
            {
                CellRange cr = this.filledcells.get(i);
                for (Point p: cr.coords)
                {
                    if (coord.equals(p))
                    {
                        for (Ship s: this.ships)
                        {
                            if (cr.ship.equals(s.model))
                            {
                                
                                s.hitShip();
                                if (!s.alive)
                                {
                                    shipkilled = true;
                                    this.tempship = s;
                                    System.out.println("You sank the enemy %s!".formatted(s.model));
                                }
                                else 
                                {
                                    System.out.println("You hit the enemy %s!".formatted(s.model));
                                }

                                flag = true;
                                tempint = i;
                                this.tempcr = cr;
                                this.tempcoord = coord;
                                this.totalhealth--;
                                
                                
                            }
                            else 
                            {
                                //System.out.println("Ship types dont match between CellRange (%s) and Ship (%s)".formatted(cr.ship, s.model));
                            }
                        }
                        
                    }

                }

            }
        }

        if (flag)
        {
            this.filledcells.get(tempint).coords.remove(tempcoord);
            if (shipkilled)
            {
                this.ships.remove(tempship);
                this.filledcells.remove(tempcr);
            }

            
            this.tempship = null;
            this.tempship = null;
            this.tempcoord = null;
        }
            

    }

    public String debugPrint ()
    {
        String t = "";

        for (Cell[] c1: board)
        {
            for (Cell c2: c1)
            {
                if (c2.sankship)
                {
                    t += " * ";
                }
                else if (c2.discovered)
                {
                    t += "%s".formatted(c2);
                }
                else
                {
                    t += "%s".formatted(c2.debug);
                }
                
            }
            t += "\n";
        }

        return t;
    }

    @Override
    public String toString ()
    {
        String t = "";

        for (Cell[] c: this.board)
        {
            for (Cell c2: c)
            {
                t += c2;
            }

            t += "\n";
        }


        return t;
    }

    
}
