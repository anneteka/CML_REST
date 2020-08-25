package main.repository.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@Document(indexName = "cml_files")
@Getter
@Setter
@ToString
public class FileDocument {
    @Id
    private String id;

    private String file_name;
    private int file_size;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<String> file_tags;
}
