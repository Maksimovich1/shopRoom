package com.mamba.shop.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class IControllerAdvice {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handler(){
        return "redirect:404";
    }
}
