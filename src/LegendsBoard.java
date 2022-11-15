/**
 * This class represents the board used in this game. It extends Board class.
 * It has all the methods used by a board, starting form initialise to endgame
 **/

import java.util.ArrayList;

public class LegendsBoard extends Board{

    public LegendsBoard(int dimh, int dimw) {
        super(dimh, dimw);
    }

    public LegendsBoard() {
        super();
    }

    @Override
    public void initBoard() {
        ArrayList<Integer> l = new ArrayList<Integer>();
        for(int i=0;i<height*width-1;i++)
            l.add(i);

        java.util.Collections.shuffle(l);

        int s=0;
        for(int a:Constants.distribution)
            s+=a;

        board[height-1][width-1]=new CommonSpace();
        board[height-1][width-1].setEmpty(false);

        for(int i=0;i<l.size();i++)
        {
            if(i<(Constants.distribution[0]*1.0/s)*l.size())
                board[l.get(i)/width][l.get(i)%width] = new InaccessibleCell();
            else if (i<((Constants.distribution[0]+Constants.distribution[1])*1.0/s)*l.size())
                board[l.get(i)/width][l.get(i)%width] = new MarketSpace();
            else
                board[l.get(i)/width][l.get(i)%width] = new CommonSpace();
        }
    }

    @Override
    public String toString() {

        String str = "\n\n\033[0;1m";
        String[] instruction = {"W/w: move up",
                "A/a: move left",
                "S/s: move down",
        "D/d: move right",
        "Q/q: quit game",
        "I/i: show information",
        "save: Save progress"};

        for(int i=0;i<height;i++)
        {
            str = getLineString(str, i);
            str += "\n";
            for(int j=0;j<width;j++)
            {
                if(board[i][j] instanceof InaccessibleCell)
                {
                    str+=Menu.bold(Menu.getColorString("| "+Constants.INACCESSIBLE+Constants.INACCESSIBLE+" |  ",Constants.BLACK));
                }
                else if(board[i][j] instanceof MarketSpace)
                {
                    if(board[i][j].isEmpty())
                        str+=Menu.bold(Menu.getColorString("| "+"  "+" |  ",Constants.BLUE));
                    else
                        str+=Menu.bold(Menu.getColorString("| "+Constants.PERSON+" |  ",Constants.BLUE));
                }
                else
                {
                    if(board[i][j].isEmpty())
                        str+=Menu.bold(Menu.getColorString("| "+"  "+" |  ",Constants.YELLOW));
                    else
                        str+=Menu.bold(Menu.getColorString("| "+Constants.PERSON+" |  ",Constants.YELLOW));
                }
            }
            str+="          ";
            if(i<instruction.length)
                str+=instruction[i];
            str += "\n";

            str = getLineString(str, i);
            str += "\n";
        }

        return str;
    }

    private String getLineString(String str, int i) {
        for(int j=0;j<width;j++)
        {
            if(board[i][j] instanceof InaccessibleCell)
            {
                str+=Menu.bold(Menu.getColorString("------  ",Constants.BLACK));
            }
            else if(board[i][j] instanceof MarketSpace)
            {
                str+=Menu.bold(Menu.getColorString("------  ",Constants.BLUE));
            }
            else
            {
                str+=Menu.bold(Menu.getColorString("------  ",Constants.YELLOW));
            }
        }
        return str;
    }

}
