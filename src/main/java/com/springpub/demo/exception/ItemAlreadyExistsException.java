package com.springpub.demo.exception;

/**
 * @author valuados
 */

public class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException(){super();}

    public ItemAlreadyExistsException(final String message){super(message);}
}
