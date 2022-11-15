/**
 * This class represents magical items in this game.
 * It is super class for all the magic elements
 **/

public class Item {
    String name;
    int cost;
    int requiredLevel;

    Item(String name, int cost, int requiredLevel)
    {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }
}
