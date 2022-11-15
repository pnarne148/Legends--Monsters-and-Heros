/**
 * This class represents Hero's Magic Spells. It extends Item
 */

public class Spell extends Item{

    int damage;
    int manaCost;
    String type;

    Spell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel);
        this.damage=damage;
        this.manaCost=manaCost;
    }
}
