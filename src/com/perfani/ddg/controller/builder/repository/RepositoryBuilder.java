package com.perfani.ddg.controller.builder.repository;

import com.perfani.ddg.model.Entity;

public class RepositoryBuilder
{
    private IRepositoryBuilder _strategy;
    
    public RepositoryBuilder(IRepositoryBuilder strategy)
    {
        _strategy = strategy;
    }
    
    public String[] execute(Entity entity)
    {
        return _strategy.execute(entity);
    }
}
