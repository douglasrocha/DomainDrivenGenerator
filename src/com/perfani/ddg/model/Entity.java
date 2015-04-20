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

package com.perfani.ddg.model;

import java.util.ArrayList;

public class Entity 
{
    private String name;
    
    private Field key;
    
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
    public Field getKey()
    {
        return key;
    }

    /**
     * @param keys the keys to set
     */
    public void setKey(Field key)
    {
        this.key = key;
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
