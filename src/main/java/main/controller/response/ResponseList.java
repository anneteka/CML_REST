package main.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import main.repository.document.File;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ResponseList {
    private int total;
    private List<File> page;
}
