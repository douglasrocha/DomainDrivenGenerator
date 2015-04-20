package com.perfani.ddg.controller.builder.repository;

import com.perfani.ddg.model.Entity;
import com.perfani.ddg.utils.StringService;

public class JavaWithJDORepositoryStrategy implements IRepositoryBuilder
{
    @Override
    public String[] execute(Entity entity)
    {
        String[] outputArray = new String[2];
        StringBuilder repositoryInterface = new StringBuilder();
        StringBuilder repositoryClass = new StringBuilder();
        
        repositoryInterface.append(getInterfacePackage());
        repositoryInterface.append("\n\n");
        repositoryInterface.append(getInterfaceImports(entity));
        repositoryInterface.append("\n\n");
        repositoryInterface.append(getInterfaceContent(entity));
        outputArray[0] = repositoryInterface.toString();
        
        repositoryClass.append(getClassPackage());        
        repositoryClass.append("\n\n");
        repositoryClass.append(getClassImports(entity));
        repositoryClass.append("\n\n");
        repositoryClass.append(getClassContent(entity));
        outputArray[1] = repositoryClass.toString();
        
        return outputArray;
    }
    
    private String getInterfacePackage()
    {
        return "package com.company.app.domain.interfaces.services;";
    }
    
    private String getInterfaceImports(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("import com.company.app.domain.entity." + entity.getName() + ";\n");
        sb.append("import com.company.app.domain.interfaces.repositories.IRepositoryBase;");
        
        return sb.toString();
    }
    
    private String getInterfaceContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        String interfaceName = StringService.getFirstCharToUpper(entity.getName());
        
        sb.append("public interface I" + interfaceName);
        sb.append("\n\textends IRepositoryBase<" + entity.getName() + ">");
        sb.append("\n{\n\n}");
        
        return sb.toString();
    }
    
    private String getClassPackage()
    {
        return "package com.company.app.infra.data.repositories;";
    }
    
    private String getClassImports(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        String className = StringService.getFirstCharToUpper(entity.getName());
        
        sb.append("import com.company.app.domain.entity." + entity.getName() + ";\n");
        sb.append("import com.company.app.domain.interfaces.repositories.I" + className + "Repository;");
        
        return sb.toString();
    }
    
    private String getClassContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        String className = StringService.getFirstCharToUpper(entity.getName());
        
        sb.append("public class " + className + "Repository\n");
        sb.append("\textends RepositoryBase<" + className + ">\n");
        sb.append("\timplements I" + className + "Repository\n");
        sb.append("{\n\n}");
        
        return sb.toString();
    }
}
