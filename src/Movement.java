/**
 * This class represents Movement in this game. It has functions to move left, right up, down.
 **/

public class Movement {

    Menu menu = new Menu();
    public boolean makeMove(Board board, Player player) {
        menu.printMoveInstructions();
        return makeNextMove(board, player);
    }

    public boolean makeNextMove(Board board, Player player) {
        String move = readMove();
        boolean valid=false;

        switch (move)
        {
            case "a":
                valid = moveLeft(board,player);
                break;
            case "s":
                valid = moveDown(board,player);
                break;
            case "w":
                valid = moveUp(board,player);
                break;
            case "d":
                valid = moveRight(board,player);
                break;
            case "i":
                valid = printInformation(player);
                break;
            case "q":
                return false;
            case "m":
                valid = market(board,player);
                break;
        }
        if(!valid)
            makeNextMove(board, player);
        return true;
    }

    private boolean moveUp(Board board, Player player) {
        if(player.position[0]<=0)
        {
            System.out.println("Invalid Move! Out of bounds!");
            return false;
        } else if (board.getCell(player.position[0]-1,player.position[1]) instanceof InaccessibleCell) {
            System.out.println("Invalid Move! That cell is inaccessible!");
            return false;
        } else{
            board.getCell(player.position[0],player.position[1]).setEmpty(true);
            player.position[0]-=1;
            board.getCell(player.position[0],player.position[1]).setEmpty(false);
            return true;
        }
    }

    private boolean moveRight(Board board, Player player) {
        if(player.position[1]+1>=board.width)
        {
            System.out.println("Invalid Move! Out of bounds!");
            return false;
        } else if (board.getCell(player.position[0],player.position[1]+1) instanceof InaccessibleCell) {
            System.out.println("Invalid Move! That cell is inaccessible!");
            return false;
        } else{
            board.getCell(player.position[0],player.position[1]).setEmpty(true);
            player.position[1]+=1;
            board.getCell(player.position[0],player.position[1]).setEmpty(false);
            return true;
        }
    }

    private boolean  printInformation(Player player) {
        System.out.println(Constants.UNDERLINE+"\nTeam information: \n================="+Constants.ANSI_RESET);
        System.out.println(player.toString());
        return false;
    }

    private boolean  quit(Board board, Player player) {
        return true;
    }

    private boolean  market(Board board, Player player) {
        return true;
    }

    private boolean  moveDown(Board board, Player player) {
        if(player.position[0]+1>= board.height)
        {
            System.out.println("Invalid Move! Out of bounds!");
            return false;
        } else if (board.getCell(player.position[0]+1,player.position[1]) instanceof InaccessibleCell) {
            System.out.println("Invalid Move! That cell is inaccessible!");
            return false;
        } else{
            board.getCell(player.position[0],player.position[1]).setEmpty(true);
            player.position[0]+=1;
            board.getCell(player.position[0],player.position[1]).setEmpty(false);
            return true;
        }
    }

    private boolean  moveLeft(Board board, Player player) {
        if(player.position[1]-1<0)
        {
            System.out.println("Invalid Move! Out of bounds!");
            return false;
        } else if (board.getCell(player.position[0],player.position[1]-1) instanceof InaccessibleCell) {
            System.out.println("Invalid Move! That cell is inaccessible!");
            return false;
        } else{
            board.getCell(player.position[0],player.position[1]).setEmpty(true);
            player.position[1]-=1;
            board.getCell(player.position[0],player.position[1]).setEmpty(false);
            return true;
        }
    }

    private String readMove() {
        String move = Menu.getString("Enter your move: ").toLowerCase();
        if(move.equals("a") || move.equals("s") || move.equals("d") || move.equals("w") || move.equals("i") || move.equals("q") || move.equals("m") || move.equals("save"))
            return move;
        else
        {
            System.out.println("Invalid input");
            return readMove();
        }
    }


}
