/**
 * This class represents Player in this game. It has all the feayures required to represent a player.
 **/

import java.io.IOException;
import java.util.ArrayList;

public class Player {

    String name;
    int count=1;
    ArrayList<Hero> characters = new ArrayList<>();
    int[] position;

    Player(int dimh,int dimw) throws IOException {
        initialisePlayer(dimh, dimw);
    }

    private void initialisePlayer(int dimh,int dimw) throws IOException {
        position= new int[]{dimh-1, dimw-1};
        name = Menu.getString("Player name is used to save the progress\nEnter player name","Legend");
        count = Menu.getInt("Enter the size of your team(1-3): ",1,3,1);
        heroSelection();
    }

    private void heroSelection() throws IOException {
        HeroCollection h = new HeroCollection();
        for(int i=0;i<count;i++)
        {
            characters.add(h.selectHero());
        }
        System.out.println("\nYour Heros are:");
        System.out.println(h.getCollectionString(characters, count));
        if(!Menu.yesOrNo("Proceed?"))
            heroSelection();
    }

    public void saveProgress()
    {
        //under construction
    }

    @Override
    public String toString() {
        return new HeroCollection().getCollectionString(characters,1);
    }

    public void printInventory()
    {
        for(Hero h : characters)
        {
            System.out.println(h.name);
            System.out.println("===================");
            System.out.println(h.inventory.toString());
        }
    }
}
