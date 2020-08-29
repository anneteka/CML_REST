package main.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//DTO-like response class
public class ResponseSuccess {
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;


    public ResponseSuccess(boolean success) {
        this.success = success;
    }
}
