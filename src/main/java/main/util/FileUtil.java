package main.util;

import main.repository.document.File;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FileUtil {
    private static Map<String, Autotags> autotags;

//    private FileUtil(){}

    //adds a tag if extension is recognised
    public static void addExtensionTags(File file){
        if (autotags==null)
            init();
        String [] parsed = file.getName().split("\\.");
        if (parsed.length<2) return;
        String extension = parsed[parsed.length-1];

        Autotags tag = autotags.getOrDefault(extension,Autotags.unrecognised);
        if (tag==Autotags.unrecognised) return;
        if (file.getTags()==null){
            HashSet<String> tags = new HashSet<>();
            tags.add(tag.name());
            file.setTags(tags);
        }
        else
            file.getTags().add(tag.name());
    }

    private static void init(){
        autotags = new HashMap<>();
        autotags.put("mp3", Autotags.audio);
        autotags.put("wav",Autotags.audio);

        autotags.put("mp4", Autotags.video);
        autotags.put("avi", Autotags.video);

        autotags.put("jpg", Autotags.image);
        autotags.put("jpeg", Autotags.image);
        autotags.put("png", Autotags.image);

        autotags.put("zip", Autotags.archive);
        autotags.put("rar", Autotags.archive);

        autotags.put("txt", Autotags.text);
        autotags.put("doc", Autotags.text);
        autotags.put("docx", Autotags.text);


    }
}

