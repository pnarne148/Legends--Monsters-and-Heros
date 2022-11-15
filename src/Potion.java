/**
 * This class represents Hero's Potion. It extends Item
 */

public class Potion extends Item{
    int attributeIncrease;
    String[] attributeAffected;
    Potion(String name, int cost, int requiredLevel, int attributeIncrease, String[] attributeAffected) {
        super(name, cost, requiredLevel);
        this.attributeAffected=attributeAffected;
        this.attributeIncrease=attributeIncrease;
    }

    public String getAttributeAffected() {
        String str = "";
        for(String s : attributeAffected )
            str+=s+", ";
        return str;
    }
}
