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
