package main.service;

import main.repository.FileRepository;
import main.repository.document.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> findAll() {
        return fileRepository.findAll();
    }


    public String save(File file){

        return fileRepository.save(file).getId();
    }

    public void deleteFile(String id){

    }

    public void addTags(){

    }
    public void deleteTags(){

    }

    public List<File> listFiles(){

        return null;
    }
}
