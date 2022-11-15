/**
 * This class represents Warrior Hero. It extends Hero class
 **/


public class Warrior extends Hero{

    public Warrior(String name, int mana, int strength, int agility, int dexterity, int gold, int experience) {
        super(name, mana, strength, agility, dexterity, gold, experience);
        this.speciality = "strength/agility";
    }

}
