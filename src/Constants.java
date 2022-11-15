/**
 * This class stores all the constants used in this project
 **/


public class Constants {

    private Constants() {
        // restrict instantiation
    }

    public static final int[] distribution = {20,30,50}; // inaccessible space, marketplace, common space
    public static final String PERSON = "\uD83C\uDFC3"; //Black box for inaccessible cells
    public static final String INACCESSIBLE = "\u2592"; //Black box for inaccessible cells

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String UNDERLINE = "\033[1m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String BLACK = "\u001B[30m";
}
