package com.aas.ssw.common.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public String nullpointerHandler(NullPointerException ex){
        return "error";
    }

}
