package com.perfani.ddg.domain.values;

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
        return replaceSlashesInWindows(replaceGenericCompanyAndAppName(application, text));
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
    
    public AppDirectories[] lala()
    {
    	return AppDirectories.values();
    }
}