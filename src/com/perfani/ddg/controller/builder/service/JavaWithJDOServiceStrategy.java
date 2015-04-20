package com.perfani.ddg.controller.builder.service;

import com.perfani.ddg.model.Entity;

public class JavaWithJDOServiceStrategy implements IServiceBuilder
{
    @Override
    public String[] execute(Entity entity)
    {
        String[] outputArray = new String[2];
        StringBuilder serviceInterface = new StringBuilder();
        StringBuilder serviceClass = new StringBuilder();
        
        serviceInterface.append(getInterfacePackage());
        serviceInterface.append("\n\n");
        serviceInterface.append(getInterfaceImports(entity));
        serviceInterface.append("\n\n");
        serviceInterface.append(getInterfaceContent(entity));
        outputArray[0] = serviceInterface.toString();
        
        serviceClass.append(getClassPackage());
        serviceClass.append("\n\n");
        serviceClass.append(getClassImports(entity));
        serviceClass.append("\n\n");
        serviceClass.append(getClassContent(entity));
        outputArray[1] = serviceClass.toString();
        
        return outputArray;
    }
    
    private String getInterfacePackage()
    {
        return "package com.company.app.domain.interfaces.services;";
    }
    
    private String getInterfaceImports(Entity entity)
    {
        return "import com.company.app.domain.entity." + entity.getName() +";";
    }
    
    private String getInterfaceContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public interface I" + entity.getName() + "Service\n");
        sb.append("\textends IServiceBase<" + entity.getName() + ">\n");
        sb.append("{\n\n}");
        
        return sb.toString();
    }
    
    private String getClassPackage()
    {
        return "package com.company.app.domain.services;";    
    }
    
    private String getClassImports(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("import com.company.app.domain.entity." + entity.getName() + ";\n");
        sb.append("import com.company.app.domain.interfaces.repositories.I" + entity.getName() + "Repository;\n");
        sb.append("import com.company.app.domain.interfaces.services.I" + entity.getName() + "Service");
        
        return sb.toString();
    }
    
    private String getClassContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public class " + entity.getName() + "Service\n");
        sb.append("\textends ServiceBase<" + entity.getName() + ">\n");
        sb.append("\timplements I" + entity.getName() + "Service\n");
        sb.append("{\n");
        sb.append("\tprivate final I" + entity.getName() + "Repository _repository;");
        sb.append("\n\n");
        sb.append("\tpublic " + entity.getName() + "Service(I" + entity.getName() + "Repository repository)\n");
        sb.append("\t{\n");
        sb.append("\t\tsuper(repository);\n");
        sb.append("\t\t_repository = repository;\n");
        sb.append("\t}\n");
        sb.append("}");
        
        return sb.toString();
    }
    
}
