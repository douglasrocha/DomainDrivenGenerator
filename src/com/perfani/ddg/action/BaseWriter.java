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
