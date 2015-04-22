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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.service.OSService;

public enum AppDirectories 
{
	SrcPath("SrcPath"),
	ComPath("ComPath"),
	CompanyPath("CompanyPath"),
	MyAppPath("MyAppPath"),
	AppPath("AppPath"),
	AppInterfacePath("AppInterfacePath"),
	DomainPath("DomainPath"),
	EntityPath("EntityPath"),
	DomainInterfacePath("DomainInterfacePath"),
	DomainRepoInterfacesPath("DomainRepoInterfacesPath"),
	DomainServiceInterfacesPath("DomainServiceInterfacesPath"),
	DomainServicePath("DomainServicePath"),
	InfraPath("InfraPath"),
	DataPath("DataPath"),
	RepositoriesPath("RepositoriesPath")
    ;

    private final String text;
    private final TechnologyType technologyType;
    private Properties properties;

    private AppDirectories(final String text) 
    {
        this.text = text;
        this.technologyType = null;
    }

    @Override
    public String toString() 
    {
        return text;
    }
    
    public String toString(Application application) throws IOException 
    {       
        if (technologyType == null || !technologyType.equals(application.getType()))
        {
            this.properties = new Properties();
            FileInputStream propFile = new FileInputStream(application.getType().toString());
            this.properties.load(propFile);
        }
        
        String path = replaceSlashesInWindows(properties.getProperty(text));        
        
        return  addsSlashIfNecessary(application.getSetupPath()) 
        		+ replaceSlashesInWindows(replaceGenericCompanyAndAppName(application, path));
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