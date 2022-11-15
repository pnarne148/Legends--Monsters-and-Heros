/**
 * This class is primarily is used to convert hashmaps from fileparser to character objects.
 *  It has useful functions like printcollection to print character systematically
 **/


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HeroCollection {

    private ArrayList<Hero> getAllSorcerers() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Sorcerers");
        ArrayList<Hero> sorcerers = new ArrayList<>();
        for(int i=0;i<map.get("Name").size();i++)
        {
            sorcerers.add(new Sorcerer(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("mana").get(i).toString()),
                    Integer.parseInt(map.get("strength").get(i).toString()),
                    Integer.parseInt(map.get("agility").get(i).toString()),
                    Integer.parseInt(map.get("dexterity").get(i).toString()),
                    Integer.parseInt(map.get("starting money").get(i).toString()),
                    Integer.parseInt(map.get("starting experience").get(i).toString())));
        }
        return sorcerers;
    }

    private ArrayList<Hero> getAllPaladins() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Paladins");
        ArrayList<Hero> paladins = new ArrayList<>();
        for(int i=0;i<map.get("Name").size();i++)
        {
            paladins.add(new Paladin(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("mana").get(i).toString()),
                    Integer.parseInt(map.get("strength").get(i).toString()),
                    Integer.parseInt(map.get("agility").get(i).toString()),
                    Integer.parseInt(map.get("dexterity").get(i).toString()),
                    Integer.parseInt(map.get("starting money").get(i).toString()),
                    Integer.parseInt(map.get("starting experience").get(i).toString())));
        }
        return paladins;
    }

    ArrayList<Hero> getAllWarriors() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Warriors");
        ArrayList<Hero> warriors = new ArrayList<>();
        for(int i=0;i<map.get("Name").size();i++)
        {
            warriors.add(new Warrior(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("mana").get(i).toString()),
                    Integer.parseInt(map.get("strength").get(i).toString()),
                    Integer.parseInt(map.get("agility").get(i).toString()),
                    Integer.parseInt(map.get("dexterity").get(i).toString()),
                    Integer.parseInt(map.get("starting money").get(i).toString()),
                    Integer.parseInt(map.get("starting experience").get(i).toString())));
        }
        return warriors;
    }

    ArrayList<Hero> getAllHeros() throws IOException {
        ArrayList<Hero> heros = new ArrayList<>();
        heros.addAll(getAllPaladins());
        heros.addAll(getAllSorcerers());
        heros.addAll(getAllWarriors());
        return heros;
    }

    public Hero selectHero() throws IOException {
        System.out.println("\nSelect the type of hero you want to fight with");
        System.out.println("<1> Warrior: favored on strength and agility");
        System.out.println("<2> Sorcerers: favored on dexterity and agility");
        System.out.println("<3> Paladins: favored on strength and dexterity");
        int c = Menu.getInt("Your choice: ",1,3,1);

        ArrayList<Hero> heros = getSelectedHeros(c);
        return getChosenHero(heros);
    }

    private Hero getChosenHero(ArrayList<Hero> heros) {
        System.out.println();
        System.out.println(getCollectionString(heros));
        int c = Menu.getInt("Your choice: ",1,heros.size(),1);
        return heros.get(c-1);
    }

    public String getCollectionString(ArrayList<Hero> collection){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Selection","Name", "HP", "Mana", "Strength", "Agility", "Dexterity", "Gold", "Experience");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList("<"+(i+1)+"> ",
                    collection.get(i).name,
                    collection.get(i).HP+"",
                    collection.get(i).MP+"",
                    collection.get(i).strength+"",
                    collection.get(i).agility+"",
                    collection.get(i).dexterity+"",
                    collection.get(i).gold+"",
                    collection.get(i).experience+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getCollectionString(ArrayList<Hero> collection, int n){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Name","HP", "Mana", "Strength", "Agility", "Dexterity", "Gold", "Experience");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList(collection.get(i).name,
                    collection.get(i).HP+"",
                    collection.get(i).MP+"",
                    collection.get(i).strength+"",
                    collection.get(i).agility+"",
                    collection.get(i).dexterity+"",
                    collection.get(i).gold+"",
                    collection.get(i).experience+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getCollectionString(Hero hero){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Name", "Mana", "Strength", "Agility", "Dexterity", "Gold", "Experience");
        rows.add(headers);

        rows.add(Arrays.asList(hero.name,
                hero.MP+"",
                hero.strength+"",
                hero.agility+"",
                hero.dexterity+"",
                hero.gold+"",
                hero.experience+""));

        return Menu.formatAsTable(rows);
    }

    private ArrayList<Hero> getSelectedHeros(int c) throws IOException {
        if(c==1)
            return getAllWarriors();
        else if(c==2)
            return getAllSorcerers();
        else
            return getAllPaladins();
    }


}
