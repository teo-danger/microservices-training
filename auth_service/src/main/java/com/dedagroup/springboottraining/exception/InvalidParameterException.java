package com.dedagroup.springboottraining.exception;

public class InvalidParameterException extends RuntimeException{

    public InvalidParameterException(String message){
         super(message);
    }

}