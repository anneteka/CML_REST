package main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.repository.document.File;
import main.util.FileUtil;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class FileUtilTest {
    @Test
    public void simpleTest() throws JsonProcessingException {
        ArrayList<File> array = new ArrayList<>();
        ArrayList<File> array2 = new ArrayList<>();
        File f1 = new File();
        File f2 = new File();
        File f3 = new File();
        File f4 = new File();
        f1.setId("id1");
        f2.setId("id2");
        f3.setId("id3");
        f4.setId("id4");
        array.add(f1);
        array.add(f2);
        array.add(f3);
        array2.add(f2);
        array2.add(f3);
        array2.add(f4);
        File[] expected = {f2,f3};
        assertArrayEquals(expected,FileUtil.intersect(array,array2).toArray());
    }

}