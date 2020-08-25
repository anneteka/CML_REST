package main.repository.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "cml_files")
public class FileDocument {
    @Id
    private String id;

    private String file_name;
    private int file_size;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<String> file_tags;
}
