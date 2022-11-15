/**
* This class is primarily is used to convert hashmaps from fileparser to character objects.
*  It has useful functions like printcollection to print character systematically
**/

import java.io.IOException;
import java.util.*;

public class MonsterCollection {

    ArrayList<Monster> dragons = new ArrayList<>();
    ArrayList<Monster> exoskeletons = new ArrayList<>();
    ArrayList<Monster> spirits = new ArrayList<>();
    ArrayList<Monster> monsters = new ArrayList<>();



    MonsterCollection()
    {
        try {
            getAllMonsters();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Monster> getAllDragons() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Dragons");
        for(int i=0;i<map.get("Name").size();i++)
        {
            dragons.add(new Dragon(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("defense").get(i).toString()),
                    Integer.parseInt(map.get("dodge chance").get(i).toString())));
        }
        return dragons;
    }

    private ArrayList<Monster> getAllExoskeleton() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Exoskeletons");
        for(int i=0;i<map.get("Name").size();i++)
        {
            exoskeletons.add(new Exoskeleton(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("defense").get(i).toString()),
                    Integer.parseInt(map.get("dodge chance").get(i).toString())));
        }
        return exoskeletons;
    }

    private ArrayList<Monster> getAllSpirit() throws IOException {
        HashMap<String,ArrayList> map= FileParser.readInformation("Spirits");
        for(int i=0;i<map.get("Name").size();i++)
        {
            spirits.add(new Spirit(map.get("Name").get(i).toString().replace("_"," "),
                    Integer.parseInt(map.get("level").get(i).toString()),
                    Integer.parseInt(map.get("damage").get(i).toString()),
                    Integer.parseInt(map.get("defense").get(i).toString()),
                    Integer.parseInt(map.get("dodge chance").get(i).toString())));
        }
        return spirits;
    }


    ArrayList<Monster> getAllMonsters() throws IOException {
        monsters.addAll(getAllDragons());
        monsters.addAll(getAllExoskeleton());
        monsters.addAll(getAllSpirit());
        return monsters;
    }

    public ArrayList<Monster> getRandomMonsters(int level, int num)
    {
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0;i<num;i++) {
            try {
                monsters.add((Monster) getRandomMonster(level).clone());
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        return monsters;
    }

    public Monster getRandomMonster(int level)
    {
        ArrayList<Monster> filteredMonsters = new ArrayList<>();

        for(Monster m:monsters)
        {
            if(m.level==level)
            {
                filteredMonsters.add(m);
            }
        }
        return filteredMonsters.get(new Random().nextInt(filteredMonsters.size()));
    }

    public String getCollectionString(ArrayList<Monster> collection){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Name", "HP", "Type", "Level", "Damage", "Defense", "Dodge");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList(collection.get(i).name,
                    collection.get(i).HP+"",
                    collection.get(i).type+"",
                    collection.get(i).level+"",
                    collection.get(i).damage+"",
                    collection.get(i).defense+"",
                    collection.get(i).dodge+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getCollectionString(ArrayList<Monster> collection, boolean select){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Selection","Name", "HP","Type", "Level", "Damage", "Defense", "Dodge");
        rows.add(headers);

        for(int i=0;i<collection.size();i++)
        {
            rows.add(Arrays.asList("<"+(i+1)+">",
                    collection.get(i).name,
                    collection.get(i).HP+"",
                    collection.get(i).type,
                    collection.get(i).level+"",
                    collection.get(i).damage+"",
                    collection.get(i).defense+"",
                    collection.get(i).dodge+""));
        }

        return Menu.formatAsTable(rows);
    }

    public String getCollectionString(Monster m){
        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("Name", "Level", "Damage", "Defense", "Dodge");
        rows.add(headers);

            rows.add(Arrays.asList(m.name,
                    m.level+"",
                    m.damage+"",
                    m.defense+"",
                    m.dodge+""));

        return Menu.formatAsTable(rows);
    }


}
