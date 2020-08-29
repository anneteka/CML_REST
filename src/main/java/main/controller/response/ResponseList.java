package main.controller.response;

import main.repository.document.File;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseList {
    private int total;
    private List<File> page;
}
