package com.perfani.ddg.test;

import java.util.ArrayList;

import com.perfani.ddg.controller.builder.entity.EntityBuilder;
import com.perfani.ddg.controller.builder.entity.JavaWithJDOEntityStrategy;
import com.perfani.ddg.controller.builder.repository.JavaWithJDORepositoryStrategy;
import com.perfani.ddg.controller.builder.repository.RepositoryBuilder;
import com.perfani.ddg.controller.builder.service.JavaWithJDOServiceStrategy;
import com.perfani.ddg.controller.builder.service.ServiceBuilder;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.model.Field;

public class TempTest
{
    public static void main(String[] args)
    {
        Entity e1 = new Entity();
        e1.setName("User");
        
        // Keys        
        Field f1 = new Field();
        f1.setType("int");
        f1.setName("id");
        
        // Fields
        ArrayList<Field> listField = new ArrayList<Field>();       
        
        Field f2 = new Field();
        f2.setType("String");
        f2.setName("username");
        listField.add(f2);
        
        Field f3 = new Field();
        f3.setType("String");
        f3.setName("password");
        listField.add(f3);
        
        // Adds lists to entity
        e1.setKey(f1);
        e1.setFields(listField);
        
        EntityBuilder builder = new EntityBuilder(new JavaWithJDOEntityStrategy());
        String output = builder.execute(e1);
        
        RepositoryBuilder rbuilder = new RepositoryBuilder(new JavaWithJDORepositoryStrategy());
        String[] rout = rbuilder.execute(e1);
        
        ServiceBuilder sbuilder = new ServiceBuilder(new JavaWithJDOServiceStrategy());
        String[] sout = sbuilder.execute(e1);
        
        System.out.println("==============ENTITY==============");
        System.out.println(output);
        System.out.println("==============REPOSITORY==============");
        System.out.println(rout[0]);
        System.out.println(rout[1]);
        System.out.println("==============SERVICE==============");
        System.out.println(sout[0]);
        System.out.println(sout[1]);
    }
}
