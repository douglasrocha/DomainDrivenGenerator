package com.perfani.ddg.controller;

import java.io.IOException;
import java.util.List;

import com.perfani.ddg.action.DomainDrivenIO;
import com.perfani.ddg.action.Parser;
import com.perfani.ddg.action.builder.appservice.AppServiceBuilder;
import com.perfani.ddg.action.builder.appservice.JavaWithJDOAppServiceStrategy;
import com.perfani.ddg.action.builder.entity.EntityBuilder;
import com.perfani.ddg.action.builder.entity.JavaWithJDOEntityStrategy;
import com.perfani.ddg.action.builder.repository.JavaWithJDORepositoryStrategy;
import com.perfani.ddg.action.builder.repository.RepositoryBuilder;
import com.perfani.ddg.action.builder.service.JavaWithJDOServiceStrategy;
import com.perfani.ddg.action.builder.service.ServiceBuilder;
import com.perfani.ddg.exceptions.EntityNotFoundException;
import com.perfani.ddg.exceptions.InvalidKeyAmountException;
import com.perfani.ddg.exceptions.InvalidMultiplicityException;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.model.Relationship;
import com.perfani.ddg.utils.IOService;

public class FileController
{
    public static boolean writeCode(String inputPath, String outputPath) 
           throws IOException, InvalidKeyAmountException, 
                  EntityNotFoundException, InvalidMultiplicityException
    {
        String fileContent = IOService.readAllLines(inputPath);
        List<Entity> listEntities = Parser.GetAllEntities(fileContent);
        List<Relationship> listRelationship = Parser.GetAllRelationships(listEntities, fileContent);
        
        if (!DomainDrivenIO.createRootDirectories(outputPath))
        {
            throw new IOException("Unable to write root directories!");
        }
        
        for (Entity entity : listEntities)
        {
            EntityBuilder entityBuilder = new EntityBuilder(new JavaWithJDOEntityStrategy());
            RepositoryBuilder repositoryBuilder = new RepositoryBuilder(new JavaWithJDORepositoryStrategy());
            ServiceBuilder serviceBuilder = new ServiceBuilder(new JavaWithJDOServiceStrategy());
            AppServiceBuilder appServiceBuilder = new AppServiceBuilder(new JavaWithJDOAppServiceStrategy());
            
            String strEntity = entityBuilder.execute(entity);
            String[] strRepository = repositoryBuilder.execute(entity);
            String[] strService = serviceBuilder.execute(entity);
            String[] strAppService = appServiceBuilder.execute(entity);
            
            // Saves entity content to file
            DomainDrivenIO.saveEntityFile(entity, outputPath, strEntity);
            
            // Saves interfaces from repository, service and app service to file
            DomainDrivenIO.saveInterfaceRepositoryFile(entity, outputPath, strRepository[0]);
            DomainDrivenIO.saveInterfaceServiceFile(entity, outputPath, strService[0]);
            DomainDrivenIO.saveInterfaceAppServiceFile(entity, outputPath, strAppService[0]);
            
            // Saves classes from repository, service and app service to file
            DomainDrivenIO.saveRepositoryFile(entity, outputPath, strRepository[1]);
            DomainDrivenIO.saveServiceFile(entity, outputPath, strService[1]);
            DomainDrivenIO.saveAppServiceFile(entity, outputPath, strAppService[1]);
        }
        
        return true;
    }
}
