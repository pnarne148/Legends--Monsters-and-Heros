/**
 * This class represents Market space in this game. It extends Cell class and reachable interface.
 * Action method in this function opens market
 **/

public class MarketSpace extends Cell implements Reachable{
    @Override
    public String toString() {
        if(isReached())
            return "M";
        else
            return " ";
    }

    @Override
    public void action(Player player) {
        Market market = new Market(player);
        market.enter();
    }
}
