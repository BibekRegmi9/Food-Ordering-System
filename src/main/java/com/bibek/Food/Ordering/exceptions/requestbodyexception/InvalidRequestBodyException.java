package com.bibek.Food.Ordering.exceptions.requestbodyexception;

import java.util.List;

public class InvalidRequestBodyException extends RuntimeException{
    private Object messageList;

    public InvalidRequestBodyException(String message) {
        super(message);
    }

    public InvalidRequestBodyException(String message, List<String> messageList) {
        super(message);
        this.messageList = messageList;
    }
}
