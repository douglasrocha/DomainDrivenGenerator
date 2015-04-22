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

package com.perfani.ddg.domain.model;

import com.perfani.ddg.domain.values.TechnologyType;

public class Application
{
	private String companyName;
	
	private String applicationName;
	
	private String dataModelPath;
	
	private String setupPath;
	
	private TechnologyType type;

	/**
	 * @return the companyName
	 */
    public String getCompanyName()
    {
	    return companyName;
    }

	/**
	 * @param companyName the companyName to set
	 */
    public void setCompanyName(String companyName)
    {
	    this.companyName = companyName;
    }

	/**
	 * @return the applicationName
	 */
    public String getApplicationName()
    {
	    return applicationName;
    }

	/**
	 * @param applicationName the applicationName to set
	 */
    public void setApplicationName(String applicationName)
    {
	    this.applicationName = applicationName;
    }

	/**
	 * @return the dataModelPath
	 */
	public String getDataModelPath() {
		return dataModelPath;
	}

	/**
	 * @param dataModelPath the dataModelPath to set
	 */
	public void setDataModelPath(String dataModelPath) {
		this.dataModelPath = dataModelPath;
	}

	/**
	 * @return the setupPath
	 */
	public String getSetupPath() 
	{
		return setupPath;
	}

	/**
	 * @param setupPath the setupPath to set
	 */
	public void setSetupPath(String setupPath) 
	{
		this.setupPath = setupPath;
	}

	/**
	 * @return the type
	 */
    public TechnologyType getType()
    {
	    return type;
    }

	/**
	 * @param type the type to set
	 */
    public void setType(TechnologyType type)
    {
	    this.type = type;
    }
}
