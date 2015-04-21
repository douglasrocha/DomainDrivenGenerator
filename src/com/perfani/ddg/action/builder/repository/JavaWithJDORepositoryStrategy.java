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

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Relationship;

public class JavaWithJDORepositoryStrategy implements IRepositoryBuilder
{
    @Override
    public String[] execute(Application application, Entity entity, List<Relationship> rel)
    {
        String[] outputArray = new String[2];
        StringBuilder repositoryInterface = new StringBuilder();
        StringBuilder repositoryClass = new StringBuilder();
        
        repositoryInterface.append(getInterfacePackage(application));
        repositoryInterface.append("\n\n");
        repositoryInterface.append(getInterfaceImports(application, entity));
        repositoryInterface.append("\n\n");
        repositoryInterface.append(getInterfaceContent(entity));
        outputArray[0] = repositoryInterface.toString();
        
        repositoryClass.append(getClassPackage(application));        
        repositoryClass.append("\n\n");
        repositoryClass.append(getClassImports(application, entity));
        repositoryClass.append("\n\n");
        repositoryClass.append(getClassContent(entity));
        outputArray[1] = repositoryClass.toString();
        
        return outputArray;
    }
    
    private String getInterfacePackage(Application app)
    {
        return String.format("package com.%s.%s.domain.interfaces.repositories;", 
			                 app.getCompanyName(), 
			                 app.getApplicationName());
    }
    
    private String getInterfaceImports(Application app, Entity entity)
    {
        return String.format("import com.%s.%s.domain.entity.%s;", 
			                 app.getCompanyName(), 
			                 app.getApplicationName(), 
			                 entity.getName());
    }
    
    private String getInterfaceContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("public interface I%sRepository", entity.getName()));
        sb.append(String.format("\n\textends IRepositoryBase<%s>", entity.getName()));
        sb.append("\n{\n\n}");
        
        return sb.toString();
    }
    
    private String getClassPackage(Application app)
    {
        return String.format("package com.%s.%s.infra.data.repositories;", 
			                 app.getCompanyName(), 
			                 app.getApplicationName());
    }
    
    private String getClassImports(Application app, Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("import com.%s.%s.domain.entity.%s;\n", 
				                app.getCompanyName(), 
				                app.getApplicationName(), 
				                entity.getName()));
        
        sb.append(String.format("import com.%s.%s.domain.interfaces.repositories.I%sRepository;", 
				                app.getCompanyName(), 
				                app.getApplicationName(), 
				                entity.getName()));
        
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
