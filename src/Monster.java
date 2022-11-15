/**
 * This class represents Monsters in this game. It extends Piece class and Cloneable interface.
 **/

public class Monster extends Piece implements Cloneable{

    int damage;
    int defense;
    int dodge;
    String speciality;
    String type;


    Monster(String name, int level, int damage, int defense, int dodge)
    {
        this.name=name;
        this.level=level;
        this.damage=damage;
        this.defense=defense;
        this.dodge=dodge;
        this.setHP(level*100);
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

}
