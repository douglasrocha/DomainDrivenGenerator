package com.perfani.ddg.controller.builder.entity;

import com.perfani.ddg.model.Entity;
import com.perfani.ddg.model.Field;
import com.perfani.ddg.utils.StringService;

public class JavaWithJDOStrategy implements IEntityBuilderStrategy
{
    @Override
    public String execute(Entity entity)
    {
        StringBuilder entityClass = new StringBuilder();
        
        entityClass.append(getPackage());
        entityClass.append("\n\n");
        entityClass.append(getImportLines());
        entityClass.append("\n\n");
        entityClass.append(getClassHeader(entity));
        entityClass.append("\n");
        entityClass.append(getAttributes(entity));
        entityClass.append(getMethods(entity));
        entityClass.append("}");
        
        return entityClass.toString();
    }
    
    private String getPackage()
    {
        return "package com.company.app.domain.entity";       
    }
    
    private String getImportLines()
    {
        StringBuilder output = new StringBuilder();
        
        output.append("import javax.jdo.annotations.IdGeneratorStrategy;\n");
        output.append("import javax.jdo.annotations.PersistenceCapable;\n");
        output.append("import javax.jdo.annotations.Persistent;\n");
        output.append("import javax.jdo.annotations.PrimaryKey;\n"); 
        
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
        
        // Keys
        for (Field key : entity.getKeys())
        {
            output.append("\t@PrimaryKey\n");
            output.append("\t@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)\n");
            output.append("\tprivate " + key.getType() + " " + key.getName() + ";\n");
            output.append("\n");
        }
        
        // Fields
        for (Field field : entity.getKeys())
        {
            output.append("\t@Persistent\n");
            output.append("\tprivate " + field.getType() + " " + field.getName() + ";\n");
            output.append("\n");
        }
        
        return output.toString();
    }
    
    private String getSingleGetMethod(Field field)
    {
        StringBuilder output = new StringBuilder();
        String firstLetterToUpper = StringService.getFirstCharToUpper(field.getName());
        
        output.append("\t/**\n * @return the " + field.getName() + "\n\t */\n");
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
        
        output.append("\t/**\n * @param sets the " + field.getName() + "\n\t */\n");
        output.append("\tpublic void set" + firstLetterToUpper + "(" + field.getType() + " " + field.getName() + ")");
        output.append("\n\t{\n");
        output.append("this." + field.getName() + " = " + field.getName() + ";");
        output.append("\n\t}\n\n");
        
        return output.toString();
    }
    
    private String getMethods(Entity entity)
    {
        StringBuilder output = new StringBuilder();
        
        // Keys
        for (Field key : entity.getKeys())
        {
            output.append(getSingleGetMethod(key));
            output.append(getSingleSetMethod(key));
        }
        
        // Fields
        for (Field field : entity.getKeys())
        {
            output.append(getSingleGetMethod(field));
            output.append(getSingleSetMethod(field));
        }
        
        return output.toString();
    }
}
