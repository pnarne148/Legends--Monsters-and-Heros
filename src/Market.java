/**
 * This class represents Market in this world.
 * It has all actions such as buy(), sell()
 **/

public class Market {

    ItemCollection ic;
    Player player;

    Market(Player player)
    {
        this.player=player;
        ic = new ItemCollection();
    }

    public void enter()
    {
        int choice=0;
        do{
            choice = marketMenu(choice);
            switch (choice)
            {
                case 1:
                    buy();
                    break;
                case 2:
                    sell();
                    break;
                case 0:
                    break;
            }

        }while(choice>0);

    }

    private void sell() {
        player.printInventory();
        System.out.println("Which hero's equipment do you wish to sell: ");
        Hero hero = getChosenHero(player);
        int choice = hero.inventory.getCategory();
        Item item = hero.inventory.getItem(choice);
        if(item==null)
            return;
        if(Menu.yesOrNo("Do you wish to sell "+item.name+" for "+(item.cost/2)+" gold? "))
        {
            hero.gold+=(item.cost/2);
            hero.inventory.removeItem(item);
            System.out.println(hero.name+ " sold "+item.name+" for "+(item.cost/2));
            System.out.println(hero.name+ "'s updated wallet: "+hero.gold);
        }

    }

    private int marketMenu(int choice) {
        if(choice==0)
            System.out.println("\nWelcome to the Market! What do you wish to do: ");
        else
            System.out.println("\nWelcome back to the Market! What do you wish to do: ");
        System.out.println("<1> Buy\n" +
                "<2> Sell\n" +
                "<0> Leave market");
        return(Menu.getInt("Your choice: ",0,2,1));
    }


    private void buy() {
        System.out.println("\nSelect a hero that wants to buy equipment: ");
        Hero hero = getChosenHero(player);
        if(hero!=null)
        {
            System.out.println("Current Balance: " + hero.gold);
            Item item = ic.getChosenItem(hero.gold,hero.level);
            if(item==null)
                buy();
            else
            {
                hero.gold -= item.cost;
                hero.inventory.addItem(item);
                System.out.println("Purchase successful! "+hero.name+" now has "+item.name+"!");
                System.out.println("Current Balance: " + hero.gold);
                if(item instanceof Weapon)
                {
                    Menu.yesOrNo("Do you want to Equip this weapon?");
                    if(hero.inventory.equipWeapon((Weapon) item))
                        System.out.println(hero.name+" is equipped with "+item.name);
                }
                else if(item instanceof Armor) {
                    Menu.yesOrNo("Do you want to Equip this Armor?");
                    if(hero.inventory.equipArmor((Armor) item))
                        System.out.println(hero.name+" is armed with "+item.name);
                }
            }
            System.out.println();
        }
    }

    private Hero getChosenHero(Player player) {
        for(int i=0; i<player.characters.size();i++)
        {
            System.out.println("<"+(i+1)+">"+" "+player.characters.get(i).name+"  (Balance: "+player.characters.get(i).gold+" )");
        }
        System.out.println("<0> Leave Market");
        int choice = Menu.getInt("Your choice: ",0,player.characters.size(),1);
        if(choice==0)
            return null;
        return player.characters.get(choice-1);
    }


}
