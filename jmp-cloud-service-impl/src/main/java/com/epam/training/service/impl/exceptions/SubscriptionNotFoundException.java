package com.epam.training.service.impl.exceptions;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}