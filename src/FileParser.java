/**
 * This class is used to parse txt files to hashmaps which are later converted to objects using helper classes.
 **/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileParser {

    public static HashMap<String, ArrayList> readInformation(String filename) throws IOException {
        HashMap<String, ArrayList> data = new HashMap<>();

        File file = new File("src\\ConfigFiles\\"+filename+".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String str;
        ArrayList<String> keys = new ArrayList<>();

        if((str = br.readLine()) != null)
            for(String s: str.split("/")) {
                keys.add(s);
                data.put(s, new ArrayList<>());
            }

        while ((str = br.readLine()) != null)
        {
            String[] values = str.replace("\t"," ").split(" ");
            for(int i=0, j=0;i<values.length;i++)
                if(values[i].length()>0)
                {
                    data.get(keys.get(j)).add(values[i]);
                    j++;
                }
        }

        return data;
    }

//    public static void main(String args[])
//    {
//        MonsterCollection mc = new MonsterCollection();
//    }

}
