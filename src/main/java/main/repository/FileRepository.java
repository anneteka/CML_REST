package main.repository;

import main.repository.document.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends ElasticsearchRepository<File, String> {

    List<File> findAll();

    File save(File file);

    Optional<File> findById(String id);

    void delete(File file);

    void deleteById(String s);


}
