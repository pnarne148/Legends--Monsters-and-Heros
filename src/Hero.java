/**
 * This class represents Hero character. It extends piece(this class can be extended to chess/tictactoe and all other board games)
 * This class has all the attributes a hero would have
 * It has level-up function to level up hero
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Hero extends Piece{

    int experience;
    int MP;
    int strength;
    int dexterity;
    int agility;
    int gold;
    Inventory inventory;
    String speciality;

    public Hero(String name,int mana, int strength, int agility, int dexterity, int gold, int experience)
    {
        this.name=name;
        this.MP=mana;
        this.strength = strength;
        this.agility=agility;
        this.dexterity=dexterity;
        this.gold=gold;
        this.experience=experience;
        this.inventory=new Inventory();
        this.level=1;
    }

    @Override
    public String toString() {
        return new HeroCollection().getCollectionString(new ArrayList<>(Arrays.asList(this)));
    }

    public void levelup() {
        level+=1;
        System.out.println(name+" leveled up to level "+level+"!!");
        MP= (int) (MP*1.1);
        HP = level*100;

        if(speciality.indexOf("strength")>0)
            strength = (int) (strength*1.1);
        else
            strength = (int) (strength*1.05);

        if(speciality.indexOf("agility")>0)
            agility = (int) (agility*1.1);
        else
            agility = (int) (agility*1.05);

        if(speciality.indexOf("dexterity")>0)
            dexterity = (int) (dexterity*1.1);
        else
            dexterity = (int) (dexterity*1.05);
    }
}
