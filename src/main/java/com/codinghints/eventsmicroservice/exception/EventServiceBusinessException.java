package com.codinghints.eventsmicroservice.exception;

public class EventServiceBusinessException extends RuntimeException {
    public EventServiceBusinessException(String message){
        super(message);
    }
}
