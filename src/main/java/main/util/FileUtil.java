package main.util;

import com.fasterxml.jackson.databind.JsonNode;
import main.repository.document.File;

import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    // return only those elements that are present in both lists
    //todo with query
    public static ArrayList<File> intersect(List<File> main, List<File> containing){
        ArrayList<File> res = new ArrayList<>();
        for (File f: main){
            if (containing.contains(f)){
                res.add(f);
            }
        }
        return res;
    }


}
