package com.perfani.ddg.controller.builder.repository;

import com.perfani.ddg.model.Entity;

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
        return "package com.company.app.domain.interfaces.repositories;";
    }
    
    private String getInterfaceImports(Entity entity)
    {
        return "import com.company.app.domain.entity." + entity.getName() + ";";
    }
    
    private String getInterfaceContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public interface I" + entity + "Repository");
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
        
        sb.append("import com.company.app.domain.entity." + entity.getName() + ";\n");
        sb.append("import com.company.app.domain.interfaces.repositories.I" + entity.getName() + "Repository;");
        
        return sb.toString();
    }
    
    private String getClassContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public class " + entity.getName() + "Repository\n");
        sb.append("\textends RepositoryBase<" + entity.getName() + ">\n");
        sb.append("\timplements I" + entity.getName() + "Repository\n");
        sb.append("{\n\n}");
        
        return sb.toString();
    }
}
