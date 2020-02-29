package com.springpub.demo.exception;

/**
 * @author valuados
 */

public class MenuItemNotFoundException extends Exception {

    public MenuItemNotFoundException(){super();}

    public MenuItemNotFoundException(final String message){super(message);}
}
