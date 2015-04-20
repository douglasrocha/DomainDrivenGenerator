package com.perfani.ddg.action.builder.appservice;

import com.perfani.ddg.model.Entity;

public class AppServiceBuilder
{
    private IAppServiceBuilder _strategy;
    
    public AppServiceBuilder(IAppServiceBuilder strategy)
    {
        _strategy = strategy;
    }
    
    public String[] execute(Entity entity)
    {
        return _strategy.execute(entity);
    }
}
