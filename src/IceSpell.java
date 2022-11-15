/**
 * This class represents Hero's Ice spell. It extends spell
 */

public class IceSpell extends Spell{
    IceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = "Ice";
    }
}
