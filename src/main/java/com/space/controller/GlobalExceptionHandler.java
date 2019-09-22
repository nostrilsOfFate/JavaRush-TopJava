package com.space.controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.space.dto.ResultView;
import com.space.exceptions.ShipNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShipNotFoundException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND) //404 ошибка
    public ResultView handleAllException(ShipNotFoundException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.NOT_FOUND, "Not Found ship E404");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400 ошибка
    public ResultView handleAllException(IllegalArgumentException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.BAD_REQUEST, "Bad Request E400");
    }
    @ExceptionHandler({NumberFormatException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400 ошибка
    public ResultView handleAllException(NumberFormatException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.BAD_REQUEST, "Bad Request E400");
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //400 ошибка
    public ResultView handleAllException(ClassCastException ex) {
        ex.printStackTrace();
        return new ResultView(HttpStatus.BAD_REQUEST, "Bad Request E400");
    }


}

