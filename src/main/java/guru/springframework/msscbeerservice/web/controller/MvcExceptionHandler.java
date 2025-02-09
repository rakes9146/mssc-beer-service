package guru.springframework.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> getConstraintException(ConstraintViolationException constraintViolationException) {

        List<String> errorMessages = new ArrayList<String>(constraintViolationException.
                getConstraintViolations().size());
        constraintViolationException.getConstraintViolations().forEach(msg -> {

            errorMessages.add(msg.getPropertyPath() + " : " + msg.getMessage());
        });
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

}
