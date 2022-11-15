/**
 * This class represents Board class. It is an abstract class which can be extended to build any boardgame
 **/


public abstract class Board {

    Cell[][] board;
    int height=3,width=3;

    public Board()
    {
        this.board = new Cell[height][width];            //by default the board is 3x3
    }

    public Board(int dimh, int dimw)
    {
        height=dimh;
        width=dimw;
        this.board = new Cell[dimh][dimw];
    }

    public abstract void initBoard();
    public abstract String toString();

    public Cell getCell(int h, int w) {
        return board[h][w];
    }
}
