package com.example.validatiotrdemo.Error;

import java.security.SignatureException;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.example.validatiotrdemo.Payload.Response.SimpleResponse;

@RestControllerAdvice
public class UserErrorHandling {

    // MethodArgumentNotValidException
    @SuppressWarnings("null")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SimpleResponse> MethodArgumentNotValidExceptionhandleException(
            MethodArgumentNotValidException e) {

        // System.out.println("----------------------------------------------------");
        // System.out.println(e.getBindingResult().getFieldError().getDefaultMessage());

        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(e.getBindingResult().getFieldError().getDefaultMessage().toString());
        simpleResponse.setStatus(HttpStatus.BAD_REQUEST);
        simpleResponse.setSuccess(false);

        return ResponseEntity.badRequest().body(simpleResponse);
    }

    // HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SimpleResponse> HttpMessageNotReadableExceptionMethode(HttpMessageNotReadableException e) {

        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage("Bad Request Body.");
        simpleResponse.setStatus(HttpStatus.BAD_REQUEST);
        simpleResponse.setSuccess(false);

        return ResponseEntity.badRequest().body(simpleResponse);

    }

    // NoSuchElementException
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<SimpleResponse> NoSuchElementExceptionhandleException(NoSuchElementException e) {

        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage("No such element found.");
        simpleResponse.setStatus(HttpStatus.NOT_FOUND);
        simpleResponse.setSuccess(false);

        return ResponseEntity.badRequest().body(simpleResponse);
    }

    // DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<SimpleResponse> DataIntegrityViolationExceptionhandleException(
            DataIntegrityViolationException e) {

        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage("Email already exists.");
        simpleResponse.setStatus(HttpStatus.BAD_REQUEST);
        simpleResponse.setSuccess(false);

        return ResponseEntity.badRequest().body(simpleResponse);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<SimpleResponse> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        SimpleResponse response = new SimpleResponse();
        response.setMessage("File too large!");
        response.setSuccess(false);
        response.setStatus(HttpStatus.EXPECTATION_FAILED);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
    }

    // SignatureException
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<SimpleResponse> SignatureExceptionhandleException(SignatureException e) {

        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage("Invalid Token.");
        simpleResponse.setStatus(HttpStatus.UNAUTHORIZED);
        simpleResponse.setSuccess(false);

        return ResponseEntity.badRequest().body(simpleResponse);
    }

}
