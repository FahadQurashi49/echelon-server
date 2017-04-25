package com.echelon.controller;

import com.echelon.exception.ErrorInfo;
import com.echelon.exception.ResourceNotFoundException;
import com.echelon.exception.StateConflictException;
import com.echelon.exception.validation.FieldErrors;
import com.echelon.messages.Messages;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Fahad Qureshi on 4/18/2017.
 */

@ControllerAdvice
public class GlobalControllerExceptionHandler extends Messages{

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler({
            StateConflictException.class,
            EmptyResultDataAccessException.class,
            DataIntegrityViolationException.class,
            JpaObjectRetrievalFailureException.class
    })
    public @ResponseBody
    ErrorInfo handleConflict(HttpServletRequest req, Exception ex) {
        return new ErrorInfo(req.getRequestURI(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public @ResponseBody ErrorInfo
    handleNotFound(HttpServletRequest req, ResourceNotFoundException ex) {
        return new ErrorInfo(req.getRequestURI(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody FieldErrors
    handleValidation (HttpServletRequest req, MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldErrors fieldErrors = new FieldErrors(req.getRequestURI(), getMsg("queue.validation.error"));
        fieldErrors.saveFieldErrors(bindingResult.getFieldErrors());
        return fieldErrors;
    }
}
