/**
 * This class represents Hero's Inventory.
 * It stores hero's magic items.
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {

    ArrayList<Item> items;
    ArrayList<Weapon> equippedWeapon;
    Armor equippedArmor;
    int handsused=0;

    Inventory()
    {
        items = new ArrayList<>();
        equippedWeapon = new ArrayList<>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public void removeItem(Item item)
    {
        items.remove(item);
        equippedWeapon.remove(item);
        if(equippedArmor.equals(item))
            equippedArmor=null;
    }

    public ArrayList<Weapon> getWeapons()
    {
        ArrayList<Weapon> w = new ArrayList<>();
        for(Item i: items)
        {
            if(i instanceof Weapon)
                w.add((Weapon) i);
        }
        return w;
    }

    public ArrayList<Armor> getArmors()
    {
        ArrayList<Armor> w = new ArrayList<>();
        for(Item i: items)
        {
            if(i instanceof Armor)
                w.add((Armor) i);
        }
        return w;
    }

    public ArrayList<Potion> getPotions()
    {
        ArrayList<Potion> w = new ArrayList<>();
        for(Item i: items)
        {
            if(i instanceof Potion)
                w.add((Potion) i);
        }
        return w;
    }

    public ArrayList<Spell> getSpells()
    {
        ArrayList<Spell> w = new ArrayList<>();
        for(Item i: items)
        {
            if(i instanceof Spell)
                w.add((Spell) i);
        }
        return w;
    }

    public boolean equipWeapon(Weapon weapon)
    {
        if(handsused+weapon.hands<=2)
        {
            equippedWeapon.add((Weapon) items.get(items.size()-1));
            handsused+=weapon.hands;
            System.out.println("Weapon Equipped sucessfully");
            return true;
        }
        else {
            System.out.println("Hands used = "+handsused+", Hands required = "+weapon.hands);

            if(weapon.hands==1 && handsused==2) {
                System.out.println("Not enough hands to equip this weapon, please select which weapon to unequip:");
                for (int i = 0; i < equippedWeapon.size(); i++) {
                    System.out.println("<" + (i + 1) + ">" + " " + equippedWeapon.get(i).name + "  ( hands: " + equippedWeapon.get(i).hands + " )");
                }
                System.out.println("<0> Cancel");

                int choice = Menu.getInt("Your choice: ", 0, equippedWeapon.size(), 1);
                if (choice != 0) {
                    equippedWeapon.remove(choice - 1);
                    equippedWeapon.add((Weapon) items.get(items.size()-1));
                    System.out.println("Weapon Equipped sucessfully");
                    return true;
                }
            } else if (weapon.hands==2) {
                if(Menu.yesOrNo("Not enough hands to equip this weapon, do you wish to unequip existing weapons?")) {
                    equippedWeapon.removeAll(equippedWeapon);
                    equippedWeapon.add((Weapon) items.get(items.size()-1));
                    handsused=2;
                    System.out.println("Weapon Equipped sucessfully");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean equipArmor(Armor item) {
        if(equippedArmor==null)
        {
            equippedArmor=(Armor) items.get(items.size()-1);
            return true;
        }
        else
        {
            System.out.println("An armor is already equipped");
            printArmorDetails(equippedArmor, item);
            if(Menu.yesOrNo("Do you want to replace Armor?"))
            {
                equippedArmor=(Armor) items.get(items.size()-1);
                return true;
            }
        }
        return false;
    }

    public String printArmorDetails(Armor collection, Armor newItem){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("", "Name", "Type", "Cost", "Level", "Damage Reduction");
        rows.add(headers);

        rows.add(Arrays.asList(
                "old",
                collection.name,
                "Armor",
                collection.cost+"",
                collection.requiredLevel+"",
                collection.damageReduction+""));

        rows.add(Arrays.asList(
                "new",
                newItem.name,
                "Armor",
                newItem.cost+"",
                newItem.requiredLevel+"",
                newItem.damageReduction+""));

        return Menu.formatAsTable(rows);
    }

    @Override
    public String toString() {
        ItemCollection ic = new ItemCollection();

        String str="";

        str+="Weapons:\n";
        if(getWeapons().size()>0)
        {
            str += ic.getWeaponCollectionString(getWeapons(), true);
            str += "\n";
        }
        else
        {
            str+="Empty\n";
        }

        str+="Armory:\n";
        if(getArmors().size()>0)
        {
            str += ic.getArmorCollectionString(getArmors(), true);
            str += "\n";
        }
        else
        {
            str+="Empty\n";
        }

        str+="Potions:\n";
        if(getPotions().size()>0)
        {
            str += ic.getPotionCollectionString(getPotions(), true);
            str += "\n";
        }
        else
        {
            str+="Empty\n";
        }

        str+="Spells:\n";
        if(getSpells().size()>0)
        {
            str += ic.getSpellCollectionString(getSpells(), true);
            str += "\n";
        }
        else
        {
            str+="Empty\n";
        }
        return str;
    }

    int getCategory() {
        System.out.println("Select the type of Item you wish to buy: ");
        System.out.println("<1> Weapon\n" +
                "<2> Armor\n" +
                "<3> Potion\n" +
                "<4> Spell\n" +
                "<0> go back");
        return(Menu.getInt("Your choice: ", 0, 4, 1));
    }

    Item getItem(int choice) {
        ItemCollection ic = new ItemCollection();
        int itemchoice=0;
        switch (choice)
        {
            case 0:
                return null;
            case 1:
                if(getWeapons().size()==0)
                    return null;
                System.out.print(ic.getWeaponCollectionString(getWeapons()));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,getWeapons().size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return getWeapons().get(itemchoice-1);
            case 2:
                if(getArmors().size()==0)
                    return null;

                System.out.print(ic.getArmorCollectionString(getArmors()));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,getArmors().size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return getArmors().get(itemchoice-1);
            case 3:
                if(getPotions().size()==0)
                    return null;
                System.out.print(ic.getPotionCollectionString(getPotions()));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,getPotions().size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return getPotions().get(itemchoice-1);
            case 4:
                if(getSpells().size()==0)
                    return null;
                System.out.print(ic.getSpellCollectionString(getSpells()));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,getSpells().size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return getSpells().get(itemchoice-1);

        }
        return null;
    }


}
