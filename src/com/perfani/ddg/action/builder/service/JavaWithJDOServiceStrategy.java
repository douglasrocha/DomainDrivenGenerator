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

package com.perfani.ddg.action.builder.service;

import java.util.List;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Relationship;

public class JavaWithJDOServiceStrategy implements IServiceBuilder
{
    @Override
    public String[] execute(Application application, Entity entity, List<Relationship> rel)
    {
        String[] outputArray = new String[2];
        StringBuilder serviceInterface = new StringBuilder();
        StringBuilder serviceClass = new StringBuilder();
        
        serviceInterface.append(getInterfacePackage(application));
        serviceInterface.append("\n\n");
        serviceInterface.append(getInterfaceImports(application, entity));
        serviceInterface.append("\n\n");
        serviceInterface.append(getInterfaceContent(entity));
        outputArray[0] = serviceInterface.toString();
        
        serviceClass.append(getClassPackage(application));
        serviceClass.append("\n\n");
        serviceClass.append(getClassImports(application, entity));
        serviceClass.append("\n\n");
        serviceClass.append(getClassContent(entity));
        outputArray[1] = serviceClass.toString();
        
        return outputArray;
    }
    
    private String getInterfacePackage(Application app)
    {
        return String.format("package com.%s.%s.domain.interfaces.services;", 
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
        
        sb.append(String.format("public interface I%sService\n", entity.getName()));
        sb.append(String.format("\textends IServiceBase<%s>\n", entity.getName()));        
        sb.append("{\n\n}");
        
        return sb.toString();
    }
    
    private String getClassPackage(Application app)
    {
        return String.format("package com.%s.%s.domain.services;", 
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
        
        sb.append(String.format("import com.%s.%s.domain.interfaces.repositories.I%sRepository;\n", 
				                app.getCompanyName(), 
				                app.getApplicationName(), 
				                entity.getName()));
        
        sb.append(String.format("import com.%s.%s.domain.interfaces.services.I%sService", 
				                app.getCompanyName(), 
				                app.getApplicationName(), 
				                entity.getName()));
        	
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
