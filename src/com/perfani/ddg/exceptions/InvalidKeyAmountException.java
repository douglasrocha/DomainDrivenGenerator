package com.perfani.ddg.exceptions;

public class InvalidKeyAmountException extends Exception
{
    private static final long serialVersionUID = -8296847760276163135L;

    public InvalidKeyAmountException()
    {
        super("There can only exist one key per entity");
    }
}
