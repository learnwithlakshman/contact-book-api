package com.careerit.cb.exception;


public class ContactAlreadyExistsException extends  RuntimeException {

    public ContactAlreadyExistsException(String message){
        super(message);
    }
}
