/**
 * This class has printer and scanner helper methods. Also has few menus.
 **/


import java.util.List;
import java.util.Scanner;

public class Menu {
    public static final String SET_PLAIN_TEXT = "\033[0;0m";
    public static final String SET_BOLD_TEXT = "\033[0;1m";


    public static String bold(String str) {
        return (SET_BOLD_TEXT + str + SET_PLAIN_TEXT);
    }
    public static String getColorString(String message, String color)
    {
        return color+message+Constants.ANSI_RESET;
    }

    public void printMoveInstructions()
    {
//        System.out.println("Move Instructions:\n" +
//                "W/w: move up\n" +
//                "A/a: move left\n" +
//                "S/s: move down\n" +
//                "D/d: move right\n" +
//                "Q/q: quit game\n" +
//                "I/i: show information\n" +
//                "M/m: enter market");
//        askEnter();
    }

    public void printInstructions()
    {
        System.out.println("The heroes and monsters exist in a made-up world. They fight because they can't get along and don't get along. " +
                "> Every time the heroes win, they gain wealth and experience. ");
        System.out.println("> This world is divided into n*n grid. Some of them are inaccesible("+Constants.INACCESSIBLE+"), while some cells in the grid has market, and all thers are common space");
        System.out.println("> Beware of the common spaces, because monsters dwell in those places. Everytime player goes to the commonspace, they roll a die and if they're unlucky they'd have to find a monster");
        System.out.println("> In the market place, heroes can spend their gold on a variety of items that will assist them fight the monsters.");
        System.out.println("The heroes' job in the game is to kill monsters and keep leveling up. Have fun!!\n");
        askEnter();
    }

    public void printIntro()
    {
        String str = "\n                    ▒█░░░ █▀▀ █▀▀▀ █▀▀ █▀▀▄ █▀▀▄ █▀▀\n"+
                "                    ▒█░░░ █▀▀ █░▀█ █▀▀ █░░█ █░░█ ▀▀█\n"+
                "                    ▒█▄▄█ ▀▀▀ ▀▀▀▀ ▀▀▀ ▀░░▀ ▀▀▀░ ▀▀▀ \n\n\n"+
                "          ▒█░▒█ █▀▀ █▀▀█ █▀▀█ █▀▀ 　 ▀█ █▀ █▀▀ 　 ▒█▀▄▀█ █▀▀█ █▀▀▄ █▀▀ ▀▀█▀▀ █▀▀ █▀▀█ █▀▀\n"+
                "          ▒█▀▀█ █▀▀ █▄▄▀ █  █ ▀▀█ 　 ░█▄█░ ▀▀█ 　 ▒█▒█▒█ █░░█ █░ █ ▀▀█  ░█░░ █▀▀ █▄▄▀ ▀▀█\n"+
                "          ▒█░▒█ ▀▀▀ ▀░▀▀ ▀▀▀▀ ▀▀▀ 　 ░░▀░░ ▀▀▀ 　 ▒█░░▒█ ▀▀▀▀ ▀░░▀ ▀▀▀ ░░▀░░ ▀▀▀ ▀░▀▀ ▀▀▀"
                ;
        System.out.println(str+"\n\n");

        String photo = "        ,   A           {}              " + "                                    ______________                               \n" +
                "        / \\, | ,        .--.                  " + "                        ,===:'.,            `-._                           \n" +
                "       |    =|= >      /.--.\\                 " + "                             `:.`---.__         `-._                       \n" +
                "        \\ /` | `       |====|                 " + "                               `:.     `--.         `.                     \n" +
                "         `   |         |`::`|                  " + "                                 \\.        `.         `.                   \n" +
                "             |     .-;`\\..../`;_.-^-._        " + "                         (,,(,    \\.         `.   ____,-`.,                \n" +
                "            /\\\\/  /  |...::..|`   :   `|     " + "                      (,'     `/   \\.   ,--.___`.'                         \n" +
                "            |:'\\ |   /'''::''|   .:.   |      " + "                  ,  ,'  ,--.  `,   \\.;'         `                         \n" +
                "             \\ /\\;-,/\\   ::  |..:::::..|    " + "                   `{D, {    \\  :    \\;                                    \n" +
                "             |\\ <` >  >._::_.| ':::::' |      " + "                     V,,'    /  /    //                                    \n" +
                "             | `\"\"`  /   ^^  |   ':'   |     " + "                     j;;    /  ,' ,-//.    ,---.      ,                    \n" +
                "             |       |       \\    :    /      " + "                     \\;'   /  ,' /  _  \\  /  _  \\   ,'/                    \n" +
                "             |       |        \\   :   /       " + "                           \\   `'  / \\  `'  / \\  `.' /                     \n" +
                "             |       |___/\\___|`-.:.-`        " + "                            `.___,'   `.__,'   `.__,'  \n"+
                "             |        \\_ || _/    `           \n" +
                "             |        <_ >< _>                 \n" +
                "             |        |  ||  |                 \n" +
                "             |        |  ||  |                 \n" +
                "             |       _\\.:||:./_               \n" +
                "             |      /____/\\____\\             \n";

        System.out.println(photo);
//        System.out.println(dragon);
        askEnter();
    }

    public void askEnter()
    {
        System.out.print("<<Press enter to continue>>");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static void askEnter(String statement)
    {
        System.out.print(statement);
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

    public static boolean yesOrNo(String statement)
    {
        String input=getString(statement+"[Y/N]: ");
        if(input.toLowerCase().equals("y"))
            return true;
        else if(input.toLowerCase().equals("n"))
            return false;
        else
            return yesOrNo(statement);
    }

    public static String getString(String Statement, String defaultStr) {
        System.out.print(Statement+" (default: "+defaultStr+"): ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if(!str.equals(""))
            defaultStr = str;
        return defaultStr;
    }

    public static String getString(String Statement) {
        System.out.print(Statement);
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    public static int getInt(String Statement, int min, int max, int defaultValue) {
        Scanner sc = new Scanner(System.in);
        String t1 = "";
        int temp;

        while(true)
        {
            System.out.print(Statement);
            if(sc.hasNextLine())
            {
                t1 = sc.nextLine();
            }

            try{
                temp = Integer.parseInt(t1);
                if(temp<min || temp>max)
                {
                    throw new ArithmeticException("Invalid - Number must be >1.");
                }
                else{
                    return temp;
                }
            }catch(ArithmeticException e){
                System.out.println("Invalid Input! Number is out of range");
            }
            catch(Exception e){
                System.out.println("Invalid Input! Enter an integer!");
            }
        }
    }

    public static int getInt(String Statement, int min, int defaultInt) {
        Scanner sc = new Scanner(System.in);
        String t1 = "";
        int temp;

        while(true)
        {
            System.out.print(Statement+" ");
            if(sc.hasNextLine())
            {
                t1 = sc.nextLine();
            }

            try{
                temp = Integer.parseInt(t1);
                if(temp<min)
                {
                    throw new ArithmeticException("Invalid - Number must be >"+min);
                }
                else{
                    return temp;
                }
            }catch(ArithmeticException e){
                System.out.println("Invalid Input! Number is out of range");
                System.out.println("Number must be >"+min);
            }
            catch(Exception e){
                System.out.println("Invalid Input! Enter an integer!");
            }
        }
    }

    public static int getInt(String Statement, int defaultInt) {
        Scanner sc = new Scanner(System.in);
        String t1 = "";
        int temp;

        while(true)
        {
            System.out.print(Statement+"(default: "+defaultInt+"): ");
            if(sc.hasNextLine())
            {
                t1 = sc.nextLine();
            }

            if(!t1.equals(""))
            {
                try{
                    temp = Integer.parseInt(t1);
                    if(temp<1)
                        throw new ArithmeticException();
                    return temp;
                }catch(ArithmeticException e){
                    System.out.println("Invalid Input! Number is out of range");
                }
                catch(Exception e){
                    System.out.println("Invalid Input! Enter an integer!");
                }
            }else{
                break;
            }
        }
        return defaultInt;
    }

    public static String formatAsTable(List<List<String>> rows)
    {
//        System.out.println();
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows)
        {
            for (int i = 0; i < row.size(); i++)
            {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths)
        {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows)
        {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }

}
