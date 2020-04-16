package com.ecw.webservice.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
    ResponseEntity errorHandler(Model modal){
        modal.addAttribute("statusCode", 404);
        modal.addAttribute("error", "Not Found");
        return new ResponseEntity(modal, HttpStatus.NOT_FOUND);
    }
}
