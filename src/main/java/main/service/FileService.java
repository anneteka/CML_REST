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


    public String save(File file) {

        return fileRepository.save(file).getId();
    }

    public boolean deleteFile(String id) {
        if (fileRepository.existsById(id))
            fileRepository.deleteById(id);
        else
            return false;
        return true;
    }

    public void addTags() {

    }

    public boolean deleteTags(String id) {
        return true;
    }

    public List<File> listFiles() {

        return null;
    }
}
