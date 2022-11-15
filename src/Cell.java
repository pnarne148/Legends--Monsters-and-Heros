/**
 * This class represents a cell in Board. It is an abstract class which can be extended to build any boardgame.
 **/


public abstract class Cell {
    int positionh;
    int positionw;
    String type;
    boolean reached=false;
    boolean empty=true;

    public int[] getPosition() {
        return new int[]{positionh, positionw};
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public abstract String toString();
}
