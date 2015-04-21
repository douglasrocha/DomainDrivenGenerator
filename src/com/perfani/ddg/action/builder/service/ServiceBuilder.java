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

package com.perfani.ddg.action.builder.service;

import java.util.List;

import com.perfani.ddg.domain.model.Application;
import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Relationship;

public class ServiceBuilder
{
    private IServiceBuilder _strategy;
    
    public ServiceBuilder(Application application)
    {    	
    	switch (application.getType())
    	{
    		case JavaWithJdo:
    			_strategy = new JavaWithJDOServiceStrategy();
    			break;
    			
    		case CSharpWithAWS:
    			break;
    	}
    }
    
    public String[] execute(Application application, Entity entity, List<Relationship> rel)
    {
        return _strategy.execute(application, entity, rel);
    }
}
