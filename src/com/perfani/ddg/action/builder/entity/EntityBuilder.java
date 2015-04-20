package com.perfani.ddg.action.builder.entity;

import com.perfani.ddg.model.Entity;

public class EntityBuilder
{
    private IEntityBuilder _strategy;
    
    public EntityBuilder(IEntityBuilder strategy)
    {
        _strategy = strategy;
    }
    
    public String execute(Entity entity)
    {
        return _strategy.execute(entity);
    }
}
