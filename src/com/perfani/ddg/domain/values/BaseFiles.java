package com.perfani.ddg.domain.values;

import com.perfani.ddg.domain.model.Application;

public enum BaseFiles 
{
	AppServiceBase("base/%s/AppServiceBase.java"),
	IAppServiceBase("base/%s/IAppServiceBase.java"),
	ServiceBase("base/%s/ServiceBase.java"),
	IServiceBase("base/%s/IServiceBase.java"),
	RepositoryBase("base/%s/RepositoryBase.java"),
	IRepositoryBase("base/%s/IRepositoryBase.java")
	;
	
	private final String text;
	
	private BaseFiles(final String text)
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
		String technologyTypeFolder = null;
		
		switch (application.getType())
		{
			case JavaWithJdo:
				technologyTypeFolder = "JavaWithJDO";
				break;
				
			case CSharpWithAWS:
				break;
		}
		
		return String.format(text, technologyTypeFolder);
	}
}
