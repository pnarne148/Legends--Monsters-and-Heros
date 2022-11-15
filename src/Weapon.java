/**
 * This class represents Hero's weapon. It extends Item
 */

public class Weapon extends Item{

    int damage;
    int hands;

    Weapon(String name, int cost, int requiredLevel, int damage, int hands) {
        super(name, cost, requiredLevel);
        this.damage=damage;
        this.hands=hands;
    }
}
