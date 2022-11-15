/**Dice class, does every action a die can do and save the latest roll
*  Users can enter no.of dies they want to play with. (default:2)
*  second constructor is used to design custom die, user can input required numbers and text to create their own die
*  Functions: roll(), hasDoubles()
**/


import java.util.Random;

public class Dice {

    int numDice = 2;
    int[] die = {1,2,3,4,5,6};
    String[] dieText = {".",":",":.","::","::.",":::"};

    int[] lastRoll = new int[numDice];

    public Dice(){}

    public Dice(int numDice){
        int[] lastRoll = new int[numDice];
        this.numDice=numDice;
    }

    public Dice(int numDice, int[] die, String[] dieText){
        int[] lastRoll = new int[numDice];
        this.numDice=numDice;
        this.die = die;
        this.dieText = dieText;
    }

    public int roll()
    {
        int score=0;
        for(int i=0;i<numDice;i++) {
            int temp = rollDie();
            lastRoll[i] = temp;
            score+=die[temp];
        }
        return score;
    }

    public boolean hasDoubles()
    {
        for(int i=0;i<numDice;i++)
            for(int j=i+1;j<numDice;j++)
                if(lastRoll[i]==lastRoll[j])
                    return true;
        return false;
    }

    private int rollDie()
    {
        Random rand = new Random();
        return rand.nextInt(die.length);
    }

    public String toString()
    {
        String str = "Your Roll is ";
        for(int i=0;i<numDice;i++)
            str+=""+dieText[lastRoll[i]]+"  ";
        return str;
    }
}
