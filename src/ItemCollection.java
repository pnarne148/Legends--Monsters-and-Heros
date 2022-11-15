/**
 * This class is primarily is used to convert hashmaps from fileparser to character objects.
 *  It has useful functions like printcollection to print character systematically
 **/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemCollection {

    ArrayList<Weapon> weapons = new ArrayList<>();
    ArrayList<Armor> armors = new ArrayList<>();
    ArrayList<Potion> potions = new ArrayList<>();
    ArrayList<Spell> iceSpells = new ArrayList<>();
    ArrayList<Spell> fireSpells = new ArrayList<>();
    ArrayList<Spell> lightningSpells = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    ItemCollection()
    {
        try {
            getAllItems();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ArrayList<Weapon> getAllWeapons() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Weaponry");
        for(int i=0;i<map.get("Name").size();i++)
        {
            weapons.add(new Weapon(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("cost").get(i).toString()),
                    Integer.parseInt(map.get("level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("required hands").get(i).toString())));
        }
        return weapons;
    }

    private ArrayList<Armor> getAllArmors() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Armory");
        for(int i=0;i<map.get("Name").size();i++)
        {
            armors.add(new Armor(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("cost").get(i).toString()),
                    Integer.parseInt(map.get("required level").get(i).toString()),
                    Integer.parseInt(map.get("damage reduction").get(i).toString())));
        }
        return armors;
    }

    private ArrayList<Potion> getAllPotions() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Potions");
        for(int i=0;i<map.get("Name").size();i++)
        {
            potions.add(new Potion(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("cost").get(i).toString()),
                    Integer.parseInt(map.get("required level").get(i).toString()),
                    Integer.parseInt(map.get("attribute increase").get(i).toString()),
                    map.get("attribute affected").get(i).toString().split("/")));
        }
        return potions;
    }

    private ArrayList<Spell> getAllIceSpells() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("IceSpells");
        for(int i=0;i<map.get("Name").size();i++)
        {
            iceSpells.add(new IceSpell(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("cost").get(i).toString()),
                    Integer.parseInt(map.get("required level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("mana cost").get(i).toString())));
        }
        return iceSpells;
    }

    private ArrayList<Spell> getAllFireSpells() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("FireSpells");
        for(int i=0;i<map.get("Name").size();i++)
        {
            fireSpells.add(new FireSpell(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("cost").get(i).toString()),
                    Integer.parseInt(map.get("required level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("mana cost").get(i).toString())));
        }
        return fireSpells;
    }

    private ArrayList<Spell> getAllLightningSpells() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("LightningSpells");
        for(int i=0;i<map.get("Name").size();i++)
        {
            lightningSpells.add(new LightningSpell(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("cost").get(i).toString()),
                    Integer.parseInt(map.get("required level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("mana cost").get(i).toString())));
        }
        return lightningSpells;
    }


    ArrayList<Item> getAllItems() throws IOException {
        items.addAll(getAllWeapons());
        items.addAll(getAllArmors());
        items.addAll(getAllPotions());
        items.addAll(getAllIceSpells());
        items.addAll(getAllLightningSpells());
        items.addAll(getAllFireSpells());
        return items;
    }

    public Item getChosenItem(int gold, int level) {
        int choice = getCategory();
        Item item = getItem(choice);
        while(item!=null && item.cost>gold && item.requiredLevel>level)
        {
            if(item.cost>gold)
                System.out.println("Not enough gold in the wallet to purchase the selected item");
            else if (item.requiredLevel>level) {
                System.out.println("cannot purchase spells at levels higher than hero's level("+level+")");
            }
            item = getChosenItem(gold,level);
        }
        return item;
    }

    private Item getItem(int choice) {
        int itemchoice=0;
        System.out.println();
        switch (choice)
        {
            case 0:
                return null;
            case 1:
                System.out.print(getWeaponCollectionString(weapons));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,weapons.size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return weapons.get(itemchoice-1);
            case 2:
                System.out.print(getArmorCollectionString(armors));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,armors.size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return armors.get(itemchoice-1);
            case 3:
                System.out.print(getPotionCollectionString(potions));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,potions.size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return potions.get(itemchoice-1);
            case 5:
                System.out.print(getSpellCollectionString(iceSpells));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,iceSpells.size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return iceSpells.get(itemchoice-1);
            case 6:
                System.out.print(getSpellCollectionString(fireSpells));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,fireSpells.size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return fireSpells.get(itemchoice-1);
            case 7:
                System.out.print(getSpellCollectionString(lightningSpells));
                System.out.println("<0> go back\n");
                itemchoice = Menu.getInt("Your choice: ",0,lightningSpells.size(),1);
                if(itemchoice==0)
                    return getItem(getCategory());
                return lightningSpells.get(itemchoice-1);

        }
        return null;
    }



    public String getWeaponCollectionString(ArrayList<Weapon> collection){
        List<List<String>> rows = new ArrayList<>();
//        Name/cost/level/damage/required hands
        List<String> headers = Arrays.asList("Selection","Name", "Type", "Cost", "Level", "Damage", "Required Hands");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList("<"+(i+1)+"> ",
                    collection.get(i).name,
                    "Weapon",
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Weapon)collection.get(i)).damage+"",
                    ((Weapon) collection.get(i)).hands+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getArmorCollectionString(ArrayList<Armor> collection){
        List<List<String>> rows = new ArrayList<>();
//        Name/cost/required level/damage reduction
        List<String> headers = Arrays.asList("Selection","Name", "Type", "Cost", "Level", "Damage Reduction");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList("<"+(i+1)+"> ",
                    collection.get(i).name,
                    "Armor",
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Armor)collection.get(i)).damageReduction+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getPotionCollectionString(ArrayList<Potion> collection){
        List<List<String>> rows = new ArrayList<>();
//        Name/cost/required level/attribute increase/attribute affected
        List<String> headers = Arrays.asList("Selection","Name", "Type", "Cost", "Level", "Attribute Increase", "attribute affected");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList("<"+(i+1)+"> ",
                    collection.get(i).name,
                    "Potion",
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Potion)collection.get(i)).attributeIncrease+"",
                    ((Potion)collection.get(i)).getAttributeAffected()));
        }

        return Menu.formatAsTable(rows);
    }

    public String getSpellCollectionString(ArrayList<Spell> collection){
        List<List<String>> rows = new ArrayList<>();
//      Name/cost/required level/damage/mana cost
        List<String> headers = Arrays.asList("Selection","Name", "Type", "Cost", "Required Level", "Damage", "Mana Cost");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList("<"+(i+1)+"> ",
                    collection.get(i).name,
                    ((Spell)collection.get(i)).type,
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Spell)collection.get(i)).damage+"",
                    ((Spell)collection.get(i)).manaCost+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getWeaponCollectionString(ArrayList<Weapon> collection, boolean serial){
        List<List<String>> rows = new ArrayList<>();
//        Name/cost/level/damage/required hands
        List<String> headers = Arrays.asList("Name", "Type", "Cost", "Level", "Damage", "Required Hands");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList(
                    collection.get(i).name,
                    "Weapon",
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Weapon)collection.get(i)).damage+"",
                    ((Weapon) collection.get(i)).hands+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getArmorCollectionString(ArrayList<Armor> collection, boolean serial){
        List<List<String>> rows = new ArrayList<>();
//        Name/cost/required level/damage reduction
        List<String> headers = Arrays.asList("Name", "Type", "Cost", "Level", "Damage Reduction");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList(
                    collection.get(i).name,
                    "Armor",
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Armor)collection.get(i)).damageReduction+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getPotionCollectionString(ArrayList<Potion> collection, boolean serial){
        List<List<String>> rows = new ArrayList<>();
//        Name/cost/required level/attribute increase/attribute affected
        List<String> headers = Arrays.asList("Name", "Type", "Cost", "Level", "Attribute Increase", "attribute affected");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList(
                    collection.get(i).name,
                    "Potion",
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Potion)collection.get(i)).attributeIncrease+"",
                    ((Potion)collection.get(i)).getAttributeAffected()));
        }

        return Menu.formatAsTable(rows);
    }

    public String getSpellCollectionString(ArrayList<Spell> collection, boolean serial){
        List<List<String>> rows = new ArrayList<>();
//      Name/cost/required level/damage/mana cost
        List<String> headers = Arrays.asList("Name", "Type", "Cost", "Required Level", "Damage", "Mana Cost");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList(
                    collection.get(i).name,
                    ((Spell)collection.get(i)).type,
                    collection.get(i).cost+"",
                    collection.get(i).requiredLevel+"",
                    ((Spell)collection.get(i)).damage+"",
                    ((Spell)collection.get(i)).manaCost+""));
        }

        return Menu.formatAsTable(rows);
    }

    private int getCategory() {
        System.out.println("\nSelect the type of Item you wish to buy: ");
        System.out.println("<1> Weapon\n" +
                "<2> Armor\n" +
                "<3> Potion\n" +
                "<4> Spell\n" +
                "<0> go back");
        int choice = Menu.getInt("Your choice: ",0,4,1);
        if(choice==4)
        {
            System.out.println("Select the type of Spell you wish to buy: ");
            System.out.println("<1> Ice Spell\n" +
                    "<2> Fire Spell\n" +
                    "<3> Lightning Spell\n" +
                    "<0> go back");
            choice += Menu.getInt("Your choice: ",0,3,1);
            if(choice==4)
                return getCategory();
        }
        return choice;
    }


}
