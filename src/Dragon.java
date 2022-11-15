/**
 * This class represents Dragon monster. It extends Monster
 **/

public class Dragon extends Monster{
    Dragon(String name, int level, int damage, int defense, int dodge) {
        super(name, level, damage, defense, dodge);
        this.speciality="damage";
        this.type="Dragon";
    }
}
