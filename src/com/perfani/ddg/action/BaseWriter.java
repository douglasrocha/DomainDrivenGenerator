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
import com.perfani.ddg.domain.values.AppDirectories;
import com.perfani.ddg.domain.values.BaseFiles;
import com.perfani.ddg.service.IOService;

public class BaseWriter
{    
    public static void writeBaseFiles(Application application) throws IOException
    {
    	// Get base file paths
        String app = BaseFiles.AppServiceBase.toString(application);
        String iapp = BaseFiles.IAppServiceBase.toString(application);
        String serv = BaseFiles.ServiceBase.toString(application);
        String iserv = BaseFiles.IServiceBase.toString(application);
        String repo = BaseFiles.RepositoryBase.toString(application);
        String irepo = BaseFiles.IRepositoryBase.toString(application);
        
        // Read content from files
        String capp = IOService.readAllLines(app);
        String ciapp = IOService.readAllLines(iapp);
        String cserv = IOService.readAllLines(serv);
        String ciserv = IOService.readAllLines(iserv);
        String crepo = IOService.readAllLines(repo);
        String cirepo = IOService.readAllLines(irepo);
        
        // Write files to their own directories
        IOService.writeFile(AppDirectories.AppPath.toString(application) + "AppServiceBase", capp);
        IOService.writeFile(AppDirectories.AppInterfacePath.toString(application) + "IAppServiceBase", ciapp);
        IOService.writeFile(AppDirectories.DomainServicePath.toString(application) + "ServiceBase", cserv);
        IOService.writeFile(AppDirectories.DomainServiceInterfacesPath.toString(application) + "IServiceBase", ciserv);
        IOService.writeFile(AppDirectories.RepositoriesPath.toString(application) + "RepositoryBase", crepo);
        IOService.writeFile(AppDirectories.DomainRepoInterfacesPath.toString(application) + "IRepositoryBase", cirepo);
    }	

}
