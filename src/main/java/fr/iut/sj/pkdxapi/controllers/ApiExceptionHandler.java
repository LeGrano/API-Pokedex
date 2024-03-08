package fr.iut.sj.pkdxapi.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.iut.sj.pkdxapi.errors.APIExceptions;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { APIExceptions.class })
    protected ResponseEntity<Object> handleApiException(APIExceptions ex, WebRequest request) {
        return handleExceptionInternal(ex,  ex.getMessage(), new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(value={RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}