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

import com.perfani.ddg.model.Application;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.utils.IOService;
import com.perfani.ddg.utils.OSService;

public class DomainDrivenIO
{
    private final static String[] _paths = 
    {
        "src",
        "src/com",
        "src/com/company",
        "src/com/company/myapp",
        "src/com/company/myapp/application",
        "src/com/company/myapp/application/interfaces",
        "src/com/company/myapp/domain",
        "src/com/company/myapp/domain/entity",
        "src/com/company/myapp/domain/interfaces",
        "src/com/company/myapp/domain/interfaces/repositories",
        "src/com/company/myapp/domain/interfaces/services",
        "src/com/company/myapp/domain/services",
        "src/com/company/myapp/infra",
        "src/com/company/myapp/infra/data",
        "src/com/company/myapp/infra/data/repositories",
    };
    
    public static boolean createRootDirectories(Application application, String path)
    {
        boolean success = true;
        String parsedPath = addsSlashIfNecessary(path);        
        
        for (String item : _paths)
        {
        	String parsed_item = item;
        	
        	parsed_item = parsed_item.replace("company", application.getCompanyName());
        	parsed_item = parsed_item.replace("myapp", application.getApplicationName());
        	
            success = IOService.createFolder
            (
                parsedPath + (OSService.isWindows() ? parsed_item.replace("/", "\\") : parsed_item)
            );
            
            if (!success)
            {
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
            parsedPath += System.getProperty("os.name").toLowerCase().contains("windows") ? "\\" : "/";
        }
        
        return parsedPath;
    }
    
    public static void saveEntityFile(Application application, Entity entity, 
    								  String path, String content) throws IOException 
    {
        String prefix = "src/com/company/myapp/domain/entity/";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        prefix = prefix.replace("company", application.getCompanyName());
        prefix = prefix.replace("myapp", application.getApplicationName());
        
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + ".java";
        IOService.writeFile(parsedPath, content);
    }
    
    public static void saveInterfaceRepositoryFile(Application application, Entity entity, 
    											   String path, String content) throws IOException 
     {
        String prefix = "src/com/company/myapp/domain/interfaces/repositories/I";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        prefix = prefix.replace("company", application.getCompanyName());
        prefix = prefix.replace("myapp", application.getApplicationName());
        
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
    
     public static void saveRepositoryFile(Application application, Entity entity, 
    		 							   String path, String content) throws IOException 
     {
        String prefix = "src/com/company/myapp/infra/data/repositories/";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        prefix = prefix.replace("company", application.getCompanyName());
        prefix = prefix.replace("myapp", application.getApplicationName());
         
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
     
     public static void saveInterfaceServiceFile(Application application, Entity entity, 
    		 									 String path, String content) throws IOException 
      {
         String prefix = "src/com/company/myapp/domain/interfaces/services/I";
         prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         prefix = prefix.replace("company", application.getCompanyName());
         prefix = prefix.replace("myapp", application.getApplicationName());
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
     
      public static void saveServiceFile(Application application, Entity entity, 
    		  							 String path, String content) throws IOException 
      {
         String prefix = "src/com/company/myapp/domain/services/";
         prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         prefix = prefix.replace("company", application.getCompanyName());
         prefix = prefix.replace("myapp", application.getApplicationName());
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
      
      public static void saveInterfaceAppServiceFile(Application application, Entity entity, 
    		  										 String path, String content) throws IOException 
       {
          String prefix = "src/com/company/myapp/application/interfaces/I";
          prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
          prefix = prefix.replace("company", application.getCompanyName());
          prefix = prefix.replace("myapp", application.getApplicationName());
          
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += prefix + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
      
       public static void saveAppServiceFile(Application application, Entity entity, 
    		   								 String path, String content) throws IOException 
       {
          String prefix = "src/com/company/myapp/application/";
          prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
          prefix = prefix.replace("company", application.getCompanyName());
          prefix = prefix.replace("myapp", application.getApplicationName());
          
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += prefix + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
}
