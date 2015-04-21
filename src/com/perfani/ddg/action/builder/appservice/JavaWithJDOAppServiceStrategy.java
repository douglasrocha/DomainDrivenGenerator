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

package com.perfani.ddg.action.builder.appservice;

import java.util.List;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Relationship;

public class JavaWithJDOAppServiceStrategy implements IAppServiceBuilder
{
    @Override
    public String[] execute(Application application, Entity entity, List<Relationship> rel)
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
        return "package com.company.app.application.interfaces;";
    }
    
    private String getInterfaceImports(Entity entity)
    {
        return "import com.company.app.domain.entity." + entity.getName() +";";
    }
    
    private String getInterfaceContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public interface I" + entity.getName() + "AppService\n");
        sb.append("\textends IAppServiceBase<" + entity.getName() + ">\n");
        sb.append("{\n\n}");
        
        return sb.toString();
    }
    
    private String getClassPackage()
    {
        return "package com.company.app.application;";    
    }
    
    private String getClassImports(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("import com.company.app.application.interfaces.I" + entity.getName() + "AppService;\n");
        sb.append("import com.company.app.domain.entity." + entity.getName() + ";\n");
        sb.append("import com.company.app.domain.interfaces.services.I" + entity.getName() + "Service");
        
        return sb.toString();
    }
    
    private String getClassContent(Entity entity)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("public class " + entity.getName() + "AppService\n");
        sb.append("\textends AppServiceBase<" + entity.getName() + ">\n");
        sb.append("\timplements I" + entity.getName() + "AppService\n");
        sb.append("{\n");
        sb.append("\tprivate final I" + entity.getName() + "Service _service;");
        sb.append("\n\n");
        sb.append("\tpublic " + entity.getName() + "AppService(I" + entity.getName() + "Service service)\n");
        sb.append("\t{\n");
        sb.append("\t\tsuper(service);\n");
        sb.append("\t\t_service = service;\n");
        sb.append("\t}\n");
        sb.append("}");
        
        return sb.toString();
    }
}
