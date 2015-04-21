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

package com.perfani.ddg.action.builder.repository;

import java.util.List;

import com.perfani.ddg.model.Application;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.model.Relationship;

public class JavaWithJDORepositoryStrategy implements IRepositoryBuilder
{
    @Override
    public String[] execute(Application application, Entity entity, List<Relationship> rel)
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
        
        sb.append("public interface I" + entity.getName() + "Repository");
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
