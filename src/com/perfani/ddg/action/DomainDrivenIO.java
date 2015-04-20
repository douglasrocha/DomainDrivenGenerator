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
        "src/com/company/app",
        "src/com/company/app/application",
        "src/com/company/app/application/interfaces",
        "src/com/company/app/domain",
        "src/com/company/app/domain/entity",
        "src/com/company/app/domain/interfaces",
        "src/com/company/app/domain/interfaces/repositories",
        "src/com/company/app/domain/interfaces/services",
        "src/com/company/app/domain/services",
        "src/com/company/app/infra",
        "src/com/company/app/infra/data",
        "src/com/company/app/infra/data/repositories",
    };
    
    public static boolean createRootDirectories(String path)
    {
        boolean success = true;
        String parsedPath = addsSlashIfNecessary(path);        
        
        for (String item : _paths)
        {
            success = IOService.createFolder
            (
                parsedPath + (OSService.isWindows() ? item.replace("/", "\\") : item)
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
    
    public static void saveEntityFile(Entity entity, String path, String content) 
           throws IOException 
    {
        String prefix = "src/com/company/app/domain/entity/";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + ".java";
        IOService.writeFile(parsedPath, content);
    }
    
    public static void saveInterfaceRepositoryFile(Entity entity, String path, String content) 
            throws IOException 
     {
        String prefix = "src/com/company/app/domain/interfaces/repositories/I";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
    
     public static void saveRepositoryFile(Entity entity, String path, String content) 
            throws IOException 
     {
        String prefix = "src/com/company/app/infra/data/repositories/";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
     
     public static void saveInterfaceServiceFile(Entity entity, String path, String content) 
             throws IOException 
      {
         String prefix = "src/com/company/app/domain/interfaces/services/I";
         prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
     
      public static void saveServiceFile(Entity entity, String path, String content) 
             throws IOException 
      {
         String prefix = "src/com/company/app/domain/services/";
         prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
      
      public static void saveInterfaceAppServiceFile(Entity entity, String path, String content) 
              throws IOException 
       {
          String prefix = "src/com/company/app/application/interfaces/I";
          prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
          
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += prefix + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
      
       public static void saveAppServiceFile(Entity entity, String path, String content) 
              throws IOException 
       {
          String prefix = "src/com/company/app/application/";
          prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
          
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += prefix + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
}
