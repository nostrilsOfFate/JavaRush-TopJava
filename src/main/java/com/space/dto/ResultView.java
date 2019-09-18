package com.space.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultView {
    @NonNull
    private HttpStatus code;
    private String systemMessage;

    public ResultView(HttpStatus code){
        this.code = code;
    }
}
