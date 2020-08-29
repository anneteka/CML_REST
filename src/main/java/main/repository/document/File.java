package main.repository.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
//import org.springframework.lang.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;


@Document(indexName = "cml_files", createIndex = false)
@Getter
@Setter
@ToString
public class File {
    @Id
    @Generated
    private String id;

    @Field(name = "file_name")
    @NotNull(message = "name can't be absent")
    @NotEmpty(message = "name can't be empty")
    private String name;
    @Positive(message = "size can't be less than 1")
    @Field(name = "file_size")
    private int size;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Field(type = FieldType.Nested, includeInParent = true, name = "file_tags")
    private Set<String> tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        File file = (File) o;

        return id.equals(file.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
