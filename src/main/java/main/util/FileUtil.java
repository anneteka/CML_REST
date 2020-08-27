package main.util;

import com.fasterxml.jackson.databind.JsonNode;
import main.repository.document.File;

import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    // возвращает список файлов только с теми элементами, которые содержаться и в первом, и во втором списке
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
