package com.perfani.core.domain.services;

import java.util.Collection;

import com.perfani.core.domain.interfaces.repositories.IRepositoryBase;
import com.perfani.core.domain.interfaces.services.IServiceBase;

public class ServiceBase<T, TT> 
       implements IServiceBase<T, TT>         
{
	private final IRepositoryBase<T> _repository;
	
    public ServiceBase(IRepositoryBase<T> repository)
    {
    	_repository = repository;
    }

	@Override
    public boolean add(T obj)
    {
        return _repository.add(obj);
    }

    @Override
    public boolean add(Collection<T> obj)
    {
	    return _repository.add(obj);
    }

    @Override
    public boolean update(T obj)
    {
        return _repository.update(obj);
    }

    @Override
    public boolean update(Collection<T> obj)
    {
	    return _repository.update(obj);
    }
    
    @Override
    public T getById(TT id)
    {
        return _repository.getById(id);
    }

    @Override
    public Collection<T> getById(Collection<TT> ids)
    {
	    return _repository.getById(ids);
    }
    
    @Override
    public boolean remove(T obj)
    {
        return _repository.remove(obj);
    }

	@Override
    public boolean remove(Collection<T> obj)
    {
	    return _repository.remove(obj);
    }
}