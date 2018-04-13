package com.aas.ssw.common.component;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView nullpointerHandler(NullPointerException ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error2");
        return modelAndView;
    }

}
