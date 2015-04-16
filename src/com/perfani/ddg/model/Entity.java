package com.perfani.ddg.model;

import java.util.ArrayList;

public class Entity 
{
    private String name;
    
    private ArrayList<Field> keys;
    
    private ArrayList<Field> fields;

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the keys
     */
    public ArrayList<Field> getKeys()
    {
        return keys;
    }

    /**
     * @param keys the keys to set
     */
    public void setKeys(ArrayList<Field> keys)
    {
        this.keys = keys;
    }

    /**
     * @return the fields
     */
    public ArrayList<Field> getFields()
    {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(ArrayList<Field> fields)
    {
        this.fields = fields;
    }
}
