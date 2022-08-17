package com.blz.employeepayroll.exception.exceptionhandler;

import com.blz.employeepayroll.exception.EmployeeNotFoundException;
import com.blz.employeepayroll.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Response> handleHiringException(EmployeeNotFoundException he) {
        Response response = new Response();
        response.setErrorCode(400);
        response.setMessage(he.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
