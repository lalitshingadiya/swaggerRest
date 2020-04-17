package com.ecw.webservice.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({EmptyResultDataAccessException.class, NoSuchElementException.class})
    ResponseEntity errorHandler(Model modal){
        modal.addAttribute("statusCode", 404);
        modal.addAttribute("error", "Not Found");
        return new ResponseEntity(modal, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity validationHandler(Model modal, MethodArgumentNotValidException  oEx){
        Map<String, String> errors = new HashMap<>();
        oEx.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        modal.addAttribute("statusCode", 400);
        modal.addAttribute("error", "Bad Request");
        modal.addAttribute("message", errors);
        return new ResponseEntity(modal, HttpStatus.BAD_REQUEST);
    }
}
