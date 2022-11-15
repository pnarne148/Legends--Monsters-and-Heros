/**
 * This class represents a piece in board game. It can be reused in any boardgame
 **/


public abstract class Piece {
    String name = "";
    int level = 1;
    int HP = 100;

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void reduceHP(int damage) {
        this.HP -= damage;
    }
}
