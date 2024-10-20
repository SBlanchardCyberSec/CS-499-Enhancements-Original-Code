package battleship;

import java.awt.Point;
import java.util.Scanner;

//import battleship.board.Board;
import battleship.game.Player;

public class Main 
{

    public static Player player;
    public static boolean victory = false;
    public static boolean flag = false;
    public static boolean debug = false;
    public static String input;
    public static Scanner scan;

    public static void main(String[] args) throws Exception
    {
        //generate board
        player = new Player(10, 10);
        scan = new Scanner(System.in);

        player.updateHealth();

        printMenu();

        while (!flag)
        {

            printBoard();

            getUserInput();

            statebasedCheck();
                                                                                    
            printHealth();
            
        }


    }

    public static void getUserInput ()
    {
        System.out.println("Enter the Column (horizontal) to shoot. (0 or less to exit)");
        int x = scan.nextInt();
        System.out.println("Enter the Row (vertical) to shoot. (0 or less to exit and 15 to enable debug printing)");
        int y = scan.nextInt();

        quitCheck(x, y);

        if (!Main.flag)
        {
            if (x != 15 && y != 15)
            {
                shoot (x - 1, y - 1);
            }
            
        }

        statebasedCheck();
    }

    public static void shoot (int x, int y)
    {

        player.board.shootCell(new Point(x, y));
        player.updateHealth();

    }
    
    public static void quitCheck (int x, int y)
    {
        if (((x <= 0 || x > player.board.sizeHorizontal) && (x != 15)) || ((y <= 0 || y > player.board.sizeVertical) && y != 15))
        {
            Main.flag = true;
            
        }

        if (x == 15 && y == 15)
        {
            Main.debug = !Main.debug;
        }

    }

    public static void statebasedCheck ()
    {
        if (player.victory)
        {
            Main.victory = true;
        }
        
        if (victory)
        {
            Main.flag = true;
        }

    }

    public static void printBoard ()
    {
        if (!Main.debug)
        {
            System.out.println(Main.player.board);
        }
        else
        {
            debugPrint();
        }

        printShips();
        

    }

    public static void printMenu ()
    {
        System.out.println("=========== Battleship ===========");
    }

    public static void printHealth ()
    {
        System.out.println("====== Remaining Health: %d ======".formatted(player.health));
    }

    public static void debugPrint()
    {
        System.out.println(player.board.debugPrint());

    }

    public static void printShips()
    {
        System.out.println("Remaining Ships: %s".formatted(player.board.ships));

    }
    
    

}
