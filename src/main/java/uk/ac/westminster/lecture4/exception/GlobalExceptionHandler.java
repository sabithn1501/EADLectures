package uk.ac.westminster.lecture4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Tells Spring that this class provides advice to controllers, including centralized exception handling.
@ControllerAdvice
public class GlobalExceptionHandler {

    //Tells Spring which exception type this handler is responsible for.
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleNotFound(
//            ProductNotFoundException ex) {
//
//        ErrorResponse error = new ErrorResponse(
//                ex.getMessage(),

//        );
//
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(error);
//    }
}

