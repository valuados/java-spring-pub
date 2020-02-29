package com.springpub.demo.exception;

/**
 * @author valuados
 */
public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
