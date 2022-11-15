/**
 * This class represents Inaccessible space in this game. It extends Cell class.
 **/
public class InaccessibleCell extends Cell{
    @Override
    public String toString() {
        if(isReached())
            return Constants.PERSON;
        else
            return " ";
    }
}
