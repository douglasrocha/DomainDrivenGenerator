package com.perfani.ddg.action.writer;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.values.TechnologyType;

public class BaseWriter
{
	private IBaseWriter _strategy;
    
    public BaseWriter(TechnologyType type)
    {    	
    	switch (type)
    	{
    		case JavaWithJdo:
    			_strategy = new JavaWithJDOBaseStrategy();
    			break;
    			
    		case CSharpWithAWS:
    			break;
    	}
    }
    
    public void execute(Application application)
    {
        _strategy.execute(application);
    }	

}
