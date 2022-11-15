/**
 * This class represents Paladin Hero. It extends Hero class
 **/

public class Paladin extends Hero{
    public Paladin(String name, int mana, int strength, int agility, int dexterity, int gold, int experience) {
        super(name, mana, strength, agility, dexterity, gold, experience);
        this.speciality="strength/dexterity";
    }
}
