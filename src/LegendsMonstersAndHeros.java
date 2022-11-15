/**
 * This class represents this game. It initialises the whole game
 **/


import java.io.IOException;

public class LegendsMonstersAndHeros {

    LegendsBoard board;
    Menu menu;
    Player player;
    Movement movement = new Movement();


    LegendsMonstersAndHeros(){}

    LegendsMonstersAndHeros(int dimh, int dimw) throws IOException {
        this.board = new LegendsBoard(dimh,dimw);
        menu = new Menu();
//        menu.printIntro();
        initialise();
    }

    public void start() {
        while(movement.makeMove(board, player))
        {
            Cell currentCell =  board.getCell(player.position[0],player.position[1]);
            if(currentCell instanceof CommonSpace)
                ((CommonSpace) currentCell).action(player);
            else
                ((MarketSpace) currentCell).action(player);

            System.out.println(board.toString());
        }
    }

    private void initialise() throws IOException {

        menu.printInstructions();
        board.initBoard();
        player = new Player(board.height, board.width);
        System.out.println("<FYI: Player is in the right bottom cell>");
        System.out.println(board.toString());
    }
}
