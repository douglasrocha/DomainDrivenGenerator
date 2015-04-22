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

package com.perfani.ddg.domain.values;

import java.io.File;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.service.OSService;

public enum AppDirectories 
{
	SrcPath("src/"),
	ComPath("src/com/"),
	CompanyPath("src/com/company/"),
	MyAppPath("src/com/company/myapp/"),
	AppPath("src/com/company/myapp/application/"),
	AppInterfacePath("src/com/company/myapp/application/interfaces/"),
	DomainPath("src/com/company/myapp/domain/"),
	EntityPath("src/com/company/myapp/domain/entity/"),
	DomainInterfacePath("src/com/company/myapp/domain/interfaces/"),
	DomainRepoInterfacesPath("src/com/company/myapp/domain/interfaces/repositories/"),
	DomainServiceInterfacesPath("src/com/company/myapp/domain/interfaces/services/"),
	DomainServicePath("src/com/company/myapp/domain/services/"),
	InfraPath("src/com/company/myapp/infra/"),
	DataPath("src/com/company/myapp/infra/data/"),
	RepositoriesPath("src/com/company/myapp/infra/data/repositories/")
    ;

    private final String text;

    private AppDirectories(final String text) 
    {
        this.text = text;
    }

    @Override
    public String toString() 
    {
        return text;
    }
    
    public String toString(Application application) 
    {
        return  addsSlashIfNecessary(application.getSetupPath()) 
        		+ replaceSlashesInWindows(replaceGenericCompanyAndAppName(application, text));
    }
    
    private String addsSlashIfNecessary(String path)
    {
         String parsedPath = path;
         
         if (!path.endsWith("/") && !path.endsWith("\\"))
         {
             parsedPath += File.separator;
         }
        
         return parsedPath;
    }
    
    private String replaceGenericCompanyAndAppName(Application application, String path)
    {
    	 return path.replace("company", application.getCompanyName())
    	   		    .replace("myapp", application.getApplicationName());
    }
    
    private String replaceSlashesInWindows(String path)
    {
    	 return OSService.isWindows() ? path.replace("/", "\\") : path;
    }
}