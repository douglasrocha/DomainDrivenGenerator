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

package com.perfani.ddg.controller;

import java.io.IOException;
import java.util.List;

import com.perfani.ddg.action.DomainDrivenIO;
import com.perfani.ddg.action.Parser;
import com.perfani.ddg.action.builder.appservice.AppServiceBuilder;
import com.perfani.ddg.action.builder.entity.EntityBuilder;
import com.perfani.ddg.action.builder.repository.RepositoryBuilder;
import com.perfani.ddg.action.builder.service.ServiceBuilder;
import com.perfani.ddg.exceptions.EntityNotFoundException;
import com.perfani.ddg.exceptions.InvalidKeyAmountException;
import com.perfani.ddg.exceptions.InvalidMultiplicityException;
import com.perfani.ddg.model.Application;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.utils.IOService;

public class FileController
{
    public static boolean writeCode(Application application, String inputPath, String outputPath) 
           throws IOException, InvalidKeyAmountException, 
                  EntityNotFoundException, InvalidMultiplicityException
    {
        String fileContent = IOService.readAllLines(inputPath);
        List<Entity> listEntities = Parser.GetAllEntities(fileContent);
        //List<Relationship> listRelationship = Parser.GetAllRelationships(listEntities, fileContent);
        
        if (!DomainDrivenIO.createRootDirectories(application, outputPath))
        {
            throw new IOException("Unable to write root directories!");
        }
        
        for (Entity entity : listEntities)
        {
            EntityBuilder entityBuilder = new EntityBuilder(application.getType());
            RepositoryBuilder repositoryBuilder = new RepositoryBuilder(application.getType());
            ServiceBuilder serviceBuilder = new ServiceBuilder(application.getType());
            AppServiceBuilder appServiceBuilder = new AppServiceBuilder(application.getType());
            
            String strEntity = entityBuilder.execute(entity);
            String[] strRepository = repositoryBuilder.execute(entity);
            String[] strService = serviceBuilder.execute(entity);
            String[] strAppService = appServiceBuilder.execute(entity);
            
            // Saves entity content to file
            DomainDrivenIO.saveEntityFile(application, entity, outputPath, strEntity);
            
            // Saves interfaces from repository, service and app service to file
            DomainDrivenIO.saveInterfaceRepositoryFile(application, entity, outputPath, strRepository[0]);
            DomainDrivenIO.saveInterfaceServiceFile(application, entity, outputPath, strService[0]);
            DomainDrivenIO.saveInterfaceAppServiceFile(application, entity, outputPath, strAppService[0]);
            
            // Saves classes from repository, service and app service to file
            DomainDrivenIO.saveRepositoryFile(application, entity, outputPath, strRepository[1]);
            DomainDrivenIO.saveServiceFile(application, entity, outputPath, strService[1]);
            DomainDrivenIO.saveAppServiceFile(application, entity, outputPath, strAppService[1]);
        }
        
        return true;
    }
}
