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
    public static boolean createRootDirectories(Application application, String path)
    {
         boolean success = true;
         String parsedPath = addsSlashIfNecessary(path);        
        
         for (AppDirectories item : AppDirectories.values())
         {
             if (!IOService.createFolder(parsedPath + item.toString(application)))
             {
            	 success = false;
                 break;
             }
         }
        
         return success;
    }
    
    private static String addsSlashIfNecessary(String path)
    {
         String parsedPath = path;
         
         if (!path.endsWith("/") && !path.endsWith("\\"))
         {
             parsedPath += System.getProperty("os.name")
                                 .toLowerCase()
                                 .contains("windows") ? "\\" : "/";
         }
        
         return parsedPath;
    }
    
    public static void saveEntityFile(Application application, Entity entity, 
                                      String path, String content) throws IOException 
    {    
         String prefix = AppDirectories.EntityPath.toString(application);
       
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + ".java";
         IOService.writeFile(parsedPath, content);
    }
    
    public static void saveInterfaceRepositoryFile(Application application, Entity entity, 
                                                   String path, String content) throws IOException 
    {
         String prefix = AppDirectories.DomainRepoInterfacesPath.toString(application);
        
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Repository.java";
         IOService.writeFile(parsedPath, content);
    }
    
    public static void saveRepositoryFile(Application application, Entity entity, 
                                          String path, String content) throws IOException 
    {
         String prefix = AppDirectories.RepositoriesPath.toString(application);
          
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Repository.java";
         IOService.writeFile(parsedPath, content);
    }
     
    public static void saveInterfaceServiceFile(Application application, Entity entity, String path, 
                                                String content) throws IOException 
    {
         String prefix = AppDirectories.DomainServiceInterfacesPath.toString(application);
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
    }
     
    public static void saveServiceFile(Application application, Entity entity, String path, 
                                       String content) throws IOException 
    {
    	 String prefix = AppDirectories.DomainServicePath.toString(application);
     
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
    }
      
    public static void saveInterfaceAppServiceFile(Application application, Entity entity, 
                                                   String path, String content) throws IOException 
    {
         String prefix = AppDirectories.AppInterfacePath.toString(application);
          
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "AppService.java";
         IOService.writeFile(parsedPath, content);
    }
      
    public static void saveAppServiceFile(Application application, Entity entity, String path, 
                                          String content) throws IOException 
    {
         String prefix = AppDirectories.AppPath.toString(application);
          
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "AppService.java";
         IOService.writeFile(parsedPath, content);
    }
}
