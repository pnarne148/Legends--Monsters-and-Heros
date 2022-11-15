/**
 * Main launcher class.
 **/

import java.io.*;


public class Main {

    public static final String ANSI_RESET = "\u001B[0m";


    public static final String ANSI_YELLOW = "\u001B[33m";
    public static void main(String[] args) throws IOException {
        LegendsMonstersAndHeros game = new LegendsMonstersAndHeros(8,8);
        game.start();
        System.out.println(Constants.PERSON);
    }
}