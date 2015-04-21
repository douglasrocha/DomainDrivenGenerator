/***
 *    Copyright 2015 Douglas Bellon Rocha
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.perfani.ddg.test;

import java.io.IOException;
import java.util.ArrayList;

import com.perfani.ddg.controller.FileController;
import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Field;
import com.perfani.ddg.domain.values.TechnologyType;
import com.perfani.ddg.exceptions.EntityNotFoundException;
import com.perfani.ddg.exceptions.InvalidKeyAmountException;
import com.perfani.ddg.exceptions.InvalidMultiplicityException;

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
        
        Application app = new Application();
        app.setCompanyName("my");
        app.setApplicationName("wow");
        app.setType(TechnologyType.JavaWithJdo);
        
        
        try
        {
            FileController.writeCode(app, "/home/douglas/input.txt", "/home/douglas/output");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidKeyAmountException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (EntityNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvalidMultiplicityException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
