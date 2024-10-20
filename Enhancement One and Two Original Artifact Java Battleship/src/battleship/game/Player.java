package battleship.game;

import battleship.board.Board;

public class Player 
{
    public Board board;
    public int health;
    public boolean victory = false;


    public Player (int boardx, int boardy)
    {
        this.board = new Board(boardx, boardy);

    }

    public void updateHealth ()
    {
        this.health = board.totalhealth;
        if (this.health == 0)
        {
            this.victory = true;
        }
    }
    
}
