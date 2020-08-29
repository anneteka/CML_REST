package main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import main.repository.document.File;
import main.util.FileUtil;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FileUtilTest {
    @Test
    public void txtTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.txt");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("text"));
    }
    @Test
    public void docTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.doc");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("text"));
    }
    @Test
    public void docxTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.docx");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("text"));
    }

    @Test
    public void mp3Test() throws JsonProcessingException {
        File file = new File();
        file.setName("name.mp3");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("audio"));
    }

    @Test
    public void wavTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.wav");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("audio"));
    }
    @Test
    public void mp4Test() throws JsonProcessingException {
        File file = new File();
        file.setName("name.mp4");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("video"));
    }
    @Test
    public void aviTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.avi");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("video"));
    }

    @Test
    public void zipTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.zip");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("archive"));
    }
    @Test
    public void rarTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.rar");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("archive"));
    }
    @Test
    public void jpgTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.jpg");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("image"));
    }
    @Test
    public void jpegTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.jpeg");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("image"));
    }
    @Test
    public void pngTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.png");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags().contains("image"));
    }

    @Test
    public void unrecognizedTest() throws JsonProcessingException {
        File file = new File();
        file.setName("name.random");
        FileUtil.addExtensionTags(file);
        assertTrue(file.getTags()==null);
    }


}