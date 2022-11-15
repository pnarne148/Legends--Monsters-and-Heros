/**
 * This class represents Spirit monster. It extends Monster
 **/

public class Spirit extends Monster{

    Spirit(String name, int level, int damage, int defense, int dodge) {
        super(name, level, damage, defense, dodge);
        this.speciality="dodge";
        this.type="Spirit";
    }
}
