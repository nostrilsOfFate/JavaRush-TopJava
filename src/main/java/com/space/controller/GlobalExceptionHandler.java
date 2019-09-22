package com.space.controller;

import com.space.dto.ResultView;
import com.space.exceptions.ShipNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShipNotFoundException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResultView handleAllException(ShipNotFoundException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.NOT_FOUND, "Not Found ship E404");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultView handleAllException(IllegalArgumentException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.BAD_REQUEST, "Bad Request E400");
    }

    @ExceptionHandler({NumberFormatException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultView handleAllException(NumberFormatException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.BAD_REQUEST, "Bad Request E400");
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResultView handleAllException(ClassCastException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.BAD_REQUEST, "Bad Request E400");
    }


}

