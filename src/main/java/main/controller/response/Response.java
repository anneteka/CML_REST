package main.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//вспомогательный класс для быстрого формирования тела ответа на запрос
public class Response {
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;


    public Response(boolean success) {
        this.success = success;
    }
}
