package com.app.notes.services.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
