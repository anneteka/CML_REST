package main.service;

import main.repository.FileRepository;
import main.repository.document.File;
import main.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        //todo audio video text image
        return fileRepository.save(file).getId();
    }

    public boolean deleteFile(String id) {
        if (fileRepository.existsById(id))
            fileRepository.deleteById(id);
        else
            return false;
        return true;
    }

    public boolean addTags(String id, List<String> addTags) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()) {
            List<String> tags = file.get().getTags();
            if (tags == null) {
                tags = addTags;
            } else
                tags.addAll(addTags);
            fileRepository.save(file.get());
            return true;
        } else
            return false;

    }

    public boolean deleteTags(String id, List<String> delTags) {
        Optional<File> file = fileRepository.findById(id);
        if (file.isPresent()) {
            List<String> tags = file.get().getTags();
            if (tags == null || !file.get().getTags().containsAll(delTags))
                return false;

            tags.removeAll(delTags);
            fileRepository.save(file.get());
            return true;
        } else
            return false;
    }

    //return filtered result
    public List<File> listFiles(List<String> tags, String q) {
        if (tags == null || tags.size() == 0) {
            if (q != null && !q.equals("")) {
                return fileRepository.findAllByNameContains(q);
            }
            return fileRepository.findAll();
        }
        List<File> temp;
        if (q != null && !q.equals(""))
            temp = fileRepository.findAllByNameContains(q);
        else
            temp = fileRepository.findAllByTagsContains(tags.get(0));
        for (String tag : tags) {
            temp = FileUtil.intersect(temp, fileRepository.findAllByTagsContains(tag));
        }
        return temp;
    }


}
