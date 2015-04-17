package com.perfani.ddg.controller.builder.entity;

import com.perfani.ddg.model.Entity;

public interface IEntityBuilderStrategy
{
    String execute(Entity entity);
}
