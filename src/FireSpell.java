/**
 * This class represents Hero's Fire spell. It extends Spell
 */

public class FireSpell extends Spell{

    FireSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type="Fire";
    }
}
