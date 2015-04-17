package com.perfani.ddg.test;

import java.util.ArrayList;

import com.perfani.ddg.controller.builder.entity.EntityBuilder;
import com.perfani.ddg.controller.builder.entity.JavaWithJDOEntityStrategy;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.model.Field;

public class TempTest
{
    public static void main(String[] args)
    {
        Entity e1 = new Entity();
        e1.setName("User");
        
        // Keys
        ArrayList<Field> listKey = new ArrayList<Field>();
        
        Field f1 = new Field();
        f1.setName("id");
        
        listKey.add(f1);
        
        // Fields
        ArrayList<Field> listField = new ArrayList<Field>();       
        
        Field f2 = new Field();
        f2.setName("username");
        listField.add(f2);
        
        Field f3 = new Field();
        f3.setName("password");
        listField.add(f3);
        
        // Adds lists to entity
        e1.setKeys(listKey);
        e1.setFields(listField);
        
        EntityBuilder builder = new EntityBuilder(new JavaWithJDOEntityStrategy());
        String output = builder.execute(e1);
        
        System.out.println(output);
    }
}
