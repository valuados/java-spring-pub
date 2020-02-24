package com.springpub.demo.exception;

public class ItemAlreadyExsists extends Exception {
    public ItemAlreadyExsists(){super();}

    public ItemAlreadyExsists(final String message){super(message);}
}
