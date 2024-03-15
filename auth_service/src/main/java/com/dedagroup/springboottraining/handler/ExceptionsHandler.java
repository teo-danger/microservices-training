package com.dedagroup.springboottraining.handler;

import com.dedagroup.springboottraining.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler
    protected ResponseEntity<String> handleInvalidParameterException(InvalidParameterException exception){
        Logger logger = LoggerFactory.getLogger(InvalidParameterException.class);
        logger.error(exception.toString());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
