package com.perfani.ddg.domain.model;

import com.perfani.ddg.domain.values.TechnologyType;

public class Application
{
	private String companyName;
	
	private String applicationName;
	
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
