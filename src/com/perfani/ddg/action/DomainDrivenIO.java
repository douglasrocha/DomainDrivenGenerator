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

package com.perfani.ddg.action;

import java.io.IOException;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.values.AppDirectories;
import com.perfani.ddg.service.IOService;

public class DomainDrivenIO
{    
    public static void createRootDirectories(Application application) throws IOException
    {        
         for (AppDirectories item : AppDirectories.values())
         {
             if (!IOService.createFolder(item.toString(application)))
             {
            	 throw new IOException("Unable to write all root directories!");
             }
         }
    }
    
    public static void saveEntityFile(Application application, Entity entity, 
                                      String content) throws IOException 
    {    
         String path = AppDirectories.EntityPath.toString(application) + entity.getName()+ ".java";
         IOService.writeFile(path, content);
    }
    
    public static void saveInterfaceRepositoryFile(Application application, Entity entity, 
                                                   String content) throws IOException 
    {
    	 String path = AppDirectories.DomainRepoInterfacesPath.toString(application) + "I" + entity.getName() + "Repository.java";
         IOService.writeFile(path, content);
    }
    
    public static void saveRepositoryFile(Application application, Entity entity, 
                                          String content) throws IOException 
    {
    	String path = AppDirectories.RepositoriesPath.toString(application) + entity.getName() + "Repository.java";
        IOService.writeFile(path, content);
    }
     
    public static void saveInterfaceServiceFile(Application application, Entity entity,  
                                                String content) throws IOException 
    {
    	 String path = AppDirectories.DomainServiceInterfacesPath.toString(application) + "I" + entity.getName() + "Service.java";
         IOService.writeFile(path, content);
    }
     
    public static void saveServiceFile(Application application, Entity entity, 
                                       String content) throws IOException 
    {
    	 String path = AppDirectories.DomainServicePath.toString(application) + entity.getName() + "Service.java";
         IOService.writeFile(path, content);
    }
      
    public static void saveInterfaceAppServiceFile(Application application, Entity entity, 
                                                   String content) throws IOException 
    {
    	 String path = AppDirectories.AppInterfacePath.toString(application) + "I" + entity.getName() + "Service.java";
         IOService.writeFile(path, content);
    }
      
    public static void saveAppServiceFile(Application application, Entity entity,  
                                          String content) throws IOException 
    {
    	 String path = AppDirectories.AppPath.toString(application) + entity.getName() + "Service.java";
         IOService.writeFile(path, content);
    }
}
