package com.perfani.ddg.exceptions;

public class EntityNotFoundException extends Exception
{
    private static final long serialVersionUID = -386134827707560072L;

    public EntityNotFoundException()
    {
        super("Could not find entity");
    }
}
