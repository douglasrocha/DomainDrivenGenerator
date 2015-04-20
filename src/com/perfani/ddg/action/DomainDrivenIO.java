package com.perfani.ddg.action;

import java.io.IOException;

import com.perfani.ddg.model.Entity;
import com.perfani.ddg.utils.IOService;

public class DomainDrivenIO
{
    public static boolean createRootDirectories(String path)
    {
        String parsedPath = path;
        
        if (!path.endsWith("/") && !path.endsWith("\\"))
        {
            parsedPath += "/";
        }
        
        return IOService.createFolder(parsedPath + "com")
               && IOService.createFolder(parsedPath + "com/company")
               && IOService.createFolder(parsedPath + "com/company/app")
               && IOService.createFolder(parsedPath + "com/company/app/application")
               && IOService.createFolder(parsedPath + "com/company/app/application/interfaces")
               && IOService.createFolder(parsedPath + "com/company/app/domain")
               && IOService.createFolder(parsedPath + "com/company/app/domain/entity")
               && IOService.createFolder(parsedPath + "com/company/app/domain/interfaces")
               && IOService.createFolder(parsedPath + "com/company/app/domain/interfaces/repositories")
               && IOService.createFolder(parsedPath + "com/company/app/domain/interfaces/services")
               && IOService.createFolder(parsedPath + "com/company/app/domain/services")
               && IOService.createFolder(parsedPath + "com/company/app/infra")
               && IOService.createFolder(parsedPath + "com/company/app/infra/data")
               && IOService.createFolder(parsedPath + "com/company/app/infra/data/repositories");

    }
    
    private static String addsSlashIfNecessary(String path)
    {
        String parsedPath = path;
        
        if (!path.endsWith("/") && !path.endsWith("\\"))
        {
            parsedPath += "/";
        }
        
        return parsedPath;
    }
    
    public static void saveEntityFile(Entity entity, String path, String content) 
           throws IOException 
    {
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += "com/company/app/domain/entity/" + entity.getName() + ".java";
        IOService.writeFile(parsedPath, content);
    }
    
    public static void saveInterfaceRepositoryFile(Entity entity, String path, String content) 
            throws IOException 
     {
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += "com/company/app/domain/interfaces/repositories/I" + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
    
     public static void saveRepositoryFile(Entity entity, String path, String content) 
            throws IOException 
     {
        String parsedPath = addsSlashIfNecessary(path);
        parsedPath += "com/company/app/infra/data/repositories/" + entity.getName() + "Repository.java";
        IOService.writeFile(parsedPath, content);
     }
     
     public static void saveInterfaceServiceFile(Entity entity, String path, String content) 
             throws IOException 
      {
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += "com/company/app/domain/interfaces/repositories/I" + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
     
      public static void saveServiceFile(Entity entity, String path, String content) 
             throws IOException 
      {
         String parsedPath = addsSlashIfNecessary(path);
         parsedPath += "com/company/app/infra/data/repositories/" + entity.getName() + "Service.java";
         IOService.writeFile(parsedPath, content);
      }
      
      public static void saveInterfaceAppServiceFile(Entity entity, String path, String content) 
              throws IOException 
       {
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += "com/company/app/domain/interfaces/repositories/I" + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
      
       public static void saveAppServiceFile(Entity entity, String path, String content) 
              throws IOException 
       {
          String parsedPath = addsSlashIfNecessary(path);
          parsedPath += "com/company/app/infra/data/repositories/" + entity.getName() + "AppService.java";
          IOService.writeFile(parsedPath, content);
       }
}
