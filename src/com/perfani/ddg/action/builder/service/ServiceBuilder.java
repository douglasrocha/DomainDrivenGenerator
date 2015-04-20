package com.perfani.ddg.action.builder.service;

import com.perfani.ddg.model.Entity;

public class ServiceBuilder
{
    private IServiceBuilder _strategy;
    
    public ServiceBuilder(IServiceBuilder strategy)
    {
        _strategy = strategy;
    }
    
    public String[] execute(Entity entity)
    {
        return _strategy.execute(entity);
    }
}
