/**
 * This class represents common space in this game. It extends Cell class and reachable interface.
 * Action method in this function starts a battle
 **/

public class CommonSpace extends Cell implements Reachable{
    @Override
    public String toString() {
        if(isReached())
            return "B";
        else
            return " ";
    }

    @Override
    public void action(Player player) {
        if(rollDice())
        {
            System.out.println("You're unlucky!! Monster spotted!!");
            Battle battle = new Battle(player.characters);
            battle.start();
        }
        else
        {
            System.out.print("> It's even, so you're safe <Press enter to continue>");
            Menu.askEnter("");
        }

    }

    private boolean rollDice() {
        Dice dice = new Dice();
        System.out.println("\nYou've entered common space, Roll the dice to continue.");
        Menu.askEnter("<Press Enter to Roll the Dice>");
        int roll = dice.roll();
        System.out.println("\n"+dice.toString()+"\n");
        return roll%2!=0;
    }
}
