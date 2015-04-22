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

package com.perfani.ddg.action.builder.entity;

import java.util.List;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Field;
import com.perfani.ddg.domain.model.Relationship;
import com.perfani.ddg.service.StringService;

public class JavaWithJDOEntityStrategy implements IEntityBuilder
{
    @Override
    public String execute(Application application, Entity entity, List<Relationship> rel)
    {
        StringBuilder entityClass = new StringBuilder();
        
        entityClass.append(getPackage(application));
        entityClass.append("\n\n");
        entityClass.append(getImportLines());
        entityClass.append("\n");
        entityClass.append(getRelationshipImportLines(application, entity, rel));
        entityClass.append(getClassHeader(entity));
        entityClass.append("\n");
        entityClass.append(getAttributes(entity));
        entityClass.append(getRelationshipAttributes(application, entity, rel));
        entityClass.append(getMethods(entity));
        entityClass.append(getRelationshipMethods(entity, rel));
        entityClass.append("}");
        
        return entityClass.toString();
    }
    
    private String getPackage(Application app)
    {
        return String.format("package com.%s.%s.domain.entity", 
        					 app.getCompanyName(), 
        					 app.getApplicationName());       
    }
    
    private String getImportLines()
    {
        StringBuilder output = new StringBuilder();
        
        output.append("import javax.jdo.annotations.IdGeneratorStrategy;\n");
        output.append("import javax.jdo.annotations.PersistenceCapable;\n");
        output.append("import javax.jdo.annotations.Persistent;\n");
        output.append("import javax.jdo.annotations.PrimaryKey;\n");
        output.append("import java.lang.Iterable;\n");
        
        return output.toString();
    }
    
    private String getRelationshipImportLines(Application app, Entity entity, List<Relationship> rel)
    {
        StringBuilder output = new StringBuilder();
        
        for (Relationship r : rel)
        {
            boolean isFrom = r.getFrom().equals(entity);
            boolean isTo = r.getTo().equals(entity);
            Entity auxEntity = null;
            
            if (isFrom)
            {
                auxEntity = r.getTo();
            }
            else if (isTo)
            {
                auxEntity = r.getFrom();
            }
            else            
            {
                // Current relationship is not valid for this iteration entity
                continue;
            }
            
            output.append(String.format("import com.%s.%s.domain.entity.%s;\n",
                                        app.getCompanyName(),
                                        app.getApplicationName(),
                                        auxEntity.getName()));
        }
        
        if (output.length() > 0)
        {
            output.append("\n");
        }
        
        return output.toString();
    }
    
    private String getClassHeader(Entity entity)
    {
        StringBuilder output = new StringBuilder();
        
        output.append("@PersistenceCapable");
        output.append("\n");
        output.append("public class ");
        output.append(entity.getName());
        output.append("\n");
        output.append("{");
        
        return output.toString();
    }

    private String getAttributes(Entity entity)
    {
        StringBuilder output = new StringBuilder();
        
        Field key = entity.getKey();
        output.append("\t@PrimaryKey\n");
        output.append("\t@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)\n");
        output.append("\tprivate " + key.getType() + " " + key.getName() + ";\n");
        output.append("\n");
        
        
        // Fields
        for (Field field : entity.getFields())
        {
            output.append("\t@Persistent\n");
            output.append("\tprivate " + field.getType() + " " + field.getName() + ";\n");
            output.append("\n");
        }
        
        return output.toString();
    }
    
    private String getRelationshipAttributes(Application app, Entity entity, List<Relationship> rel)
    {
        StringBuilder output = new StringBuilder();
        
        for (Relationship r : rel)
        {
            boolean isFrom = r.getFrom().equals(entity);
            boolean isTo = r.getTo().equals(entity);
            String multiplicity = null;
            Entity auxEntity = null;
            
            if (isFrom)
            {
                multiplicity = r.getNTo();
                auxEntity = r.getTo();
            }
            else if (isTo)
            {
                multiplicity = r.getNFrom();
                auxEntity = r.getFrom();
            }
            else            
            {
                // Current relationship is not valid for this iteration entity
                continue;
            }
            
            if (multiplicity.equals("*"))
            {
                output.append("\t@Persistent\n");
                output.append(String.format("\tprivate Iterable<%s> list%s;\n\n",
                                            auxEntity.getName(),
                                            auxEntity.getName()));
            }
            else if (multiplicity.equals("1"))
            {
                output.append("\t@Persistent\n");
                output.append(String.format("\tprivate %s %s;\n\n",
                                            auxEntity.getName(),
                                            auxEntity.getName().toLowerCase()));
            }
        }
        
        return output.toString();
    }
    
    private String getSingleGetMethod(Field field)
    {
        StringBuilder output = new StringBuilder();
        String firstLetterToUpper = StringService.getFirstCharToUpper(field.getName());
        
        output.append("\t/**\n\t * @return the " + field.getName() + "\n\t */\n");
        output.append("\tpublic " + field.getType() + " get" + firstLetterToUpper + "()");
        output.append("\n\t{\n");
        output.append("\t\treturn " + field.getName() + ";");
        output.append("\n\t}\n\n");
        
        return output.toString();
    }
    
    private String getSingleSetMethod(Field field)
    {
        StringBuilder output = new StringBuilder();
        String firstLetterToUpper = StringService.getFirstCharToUpper(field.getName());
        
        output.append("\t/**\n\t * @param sets the " + field.getName() + "\n\t */\n");
        output.append("\tpublic void set" + firstLetterToUpper + "(" + field.getType() + " " + field.getName() + ")");
        output.append("\n\t{\n");
        output.append("\t\tthis." + field.getName() + " = " + field.getName() + ";");
        output.append("\n\t}\n\n");
        
        return output.toString();
    }
    
    private String getMethods(Entity entity)
    {
        StringBuilder output = new StringBuilder();
        
        // Key
        output.append(getSingleGetMethod(entity.getKey()));
        output.append(getSingleSetMethod(entity.getKey()));
        
        // Fields
        for (Field field : entity.getFields())
        {
            output.append(getSingleGetMethod(field));
            output.append(getSingleSetMethod(field));
        }
        
        return output.toString();
    }
    
    private String getRelationshipMethods(Entity entity, List<Relationship> rel)
    {
        StringBuilder output = new StringBuilder();
        
        for (Relationship r : rel)
        {
            boolean isFrom = r.getFrom().equals(entity);
            boolean isTo = r.getTo().equals(entity);
            String multiplicity = null;
            Entity auxEntity = null;
            Field field = new Field();
            
            if (isFrom)
            {
                multiplicity = r.getNTo();
                auxEntity = r.getTo();
            }
            else if (isTo)
            {
                multiplicity = r.getNFrom();
                auxEntity = r.getFrom();
            }
            else            
            {
                // Current relationship is not valid for this iteration entity
                continue;
            }
            
            if (multiplicity.equals("*"))
            {
                field.setName(String.format("list%s", auxEntity.getName()));
                field.setType(String.format("Iterable<%s>", auxEntity.getName()));
            }
            else if (multiplicity.equals("1"))
            {
                field.setName(String.format("%s", auxEntity.getName().toLowerCase()));
                field.setType(String.format("%s", auxEntity.getName()));
            }
            
            output.append(getSingleGetMethod(field));
            output.append(getSingleSetMethod(field));
        }
        
        return output.toString();
    }
}
