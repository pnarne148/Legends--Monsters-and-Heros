/**
 * This class represents Hero's Lightning spell. It extends spell
 */

public class LightningSpell extends Spell{

    LightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type="Lightning";
    }
}
