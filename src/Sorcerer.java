/**
 * This class represents Sorcerer Hero. It extends Hero class
 **/

public class Sorcerer extends Hero{
    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int gold, int experience) {
        super(name, mana, strength, agility, dexterity, gold, experience);
        this.speciality="dexterity/agility";
    }
}
