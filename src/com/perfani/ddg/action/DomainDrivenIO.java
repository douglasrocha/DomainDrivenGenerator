package com.perfani.ddg.action;

import java.io.IOException;

import com.perfani.ddg.model.Entity;
import com.perfani.ddg.utils.IOService;
import com.perfani.ddg.utils.OSService;

public class DomainDrivenIO
{
    private final static String[] _paths = 
    {
        "com",
        "com/company",
        "com/company/app",
        "com/company/app/application",
        "com/company/app/application/interfaces",
        "com/company/app/domain",
        "com/company/app/domain/entity",
        "com/company/app/domain/interfaces",
        "com/company/app/domain/interfaces/repositories",
        "com/company/app/domain/interfaces/services",
        "com/company/app/domain/services",
        "com/company/app/infra",
        "com/company/app/data",
        "com/company/app/data/repositories",
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
        String prefix = "com/company/app/domain/entity/";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + ".java";
        IOService.writeFile(parsedPath, content);
    }
    
    public static void saveInterfaceRepositoryFile(Entity entity, String path, String content) 
            throws IOException 
     {
        String prefix = "com/company/app/domain/interfaces/repositories/I";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
        
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
    
     public static void saveRepositoryFile(Entity entity, String path, String content) 
            throws IOException 
     {
        String prefix = "com/company/app/infra/data/repositories/";
        prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += prefix + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
     
     public static void saveInterfaceServiceFile(Entity entity, String path, String content) 
             throws IOException 
      {
         String prefix = "com/company/app/domain/interfaces/repositories/I";
         prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
     
      public static void saveServiceFile(Entity entity, String path, String content) 
             throws IOException 
      {
         String prefix = "com/company/app/infra/data/repositories/";
         prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
         
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += prefix + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
      
      public static void saveInterfaceAppServiceFile(Entity entity, String path, String content) 
              throws IOException 
       {
          String prefix = "com/company/app/domain/interfaces/repositories/I";
          prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
          
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += prefix + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
      
       public static void saveAppServiceFile(Entity entity, String path, String content) 
              throws IOException 
       {
          String prefix = "com/company/app/infra/data/repositories/";
          prefix = OSService.isWindows() ? prefix.replace("/", "\\") : prefix;
          
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += prefix + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
}
