/**
 * This class represents Hero's Armor. It extends Item
 */

public class Armor extends Item{

    int damageReduction;
    Armor(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction=damageReduction;
    }
}
