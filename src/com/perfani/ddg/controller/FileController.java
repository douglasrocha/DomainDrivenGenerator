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

import com.perfani.ddg.action.BaseWriter;
import com.perfani.ddg.action.DomainDrivenIO;
import com.perfani.ddg.action.Parser;
import com.perfani.ddg.action.builder.appservice.AppServiceBuilder;
import com.perfani.ddg.action.builder.entity.EntityBuilder;
import com.perfani.ddg.action.builder.repository.RepositoryBuilder;
import com.perfani.ddg.action.builder.service.ServiceBuilder;
import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Relationship;
import com.perfani.ddg.exceptions.EntityNotFoundException;
import com.perfani.ddg.exceptions.InvalidKeyAmountException;
import com.perfani.ddg.exceptions.InvalidMultiplicityException;
import com.perfani.ddg.service.IOService;

public class FileController
{
    public static boolean writeCode(Application application) 
           throws IOException, InvalidKeyAmountException, 
                  EntityNotFoundException, InvalidMultiplicityException
    {
        String fileContent = IOService.readAllLines(application.getDataModelPath());
        List<Entity> listEntities = Parser.GetAllEntities(fileContent);
        List<Relationship> listRelationship = Parser.GetAllRelationships(listEntities, fileContent);
        
        DomainDrivenIO.createRootDirectories(application);
        
        // Create base files
        BaseWriter.writeBaseFiles(application);
        
        // Write entity specific files (domain entity, repository, service, appservice)
        for (Entity entity : listEntities)
        {
            EntityBuilder entityBuilder = new EntityBuilder(application);
            RepositoryBuilder repositoryBuilder = new RepositoryBuilder(application);
            ServiceBuilder serviceBuilder = new ServiceBuilder(application);
            AppServiceBuilder appServiceBuilder = new AppServiceBuilder(application);
            
            String strEntity = entityBuilder.execute(application, entity, listRelationship);
            String[] strRepository = repositoryBuilder.execute(application, entity, listRelationship);
            String[] strService = serviceBuilder.execute(application, entity, listRelationship);
            String[] strAppService = appServiceBuilder.execute(application, entity, listRelationship);
            
            // Saves entity content to file
            DomainDrivenIO.saveEntityFile(application, entity, strEntity);
            
            // Saves interfaces from repository, service and app service to file
            DomainDrivenIO.saveInterfaceRepositoryFile(application, entity, strRepository[0]);
            DomainDrivenIO.saveInterfaceServiceFile(application, entity, strService[0]);
            DomainDrivenIO.saveInterfaceAppServiceFile(application, entity, strAppService[0]);
            
            // Saves classes from repository, service and app service to file
            DomainDrivenIO.saveRepositoryFile(application, entity, strRepository[1]);
            DomainDrivenIO.saveServiceFile(application, entity, strService[1]);
            DomainDrivenIO.saveAppServiceFile(application, entity, strAppService[1]);
        }
        
        return true;
    }
}
