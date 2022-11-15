/**
 * This class represents Exoskeleton monster. It extends Monster
 **/

public class Exoskeleton extends Monster{
    Exoskeleton(String name, int level, int damage, int defense, int dodge) {
        super(name, level, damage, defense, dodge);
        this.speciality="defense";
        this.type="Exoskeleton";
    }
}
