package com.springpub.demo.exception;

/**
 * @author valuados
 */

public class NoSuchMenuItemException  extends Exception {

    public NoSuchMenuItemException(){super();}

    public NoSuchMenuItemException(final String message){super(message);}
}
