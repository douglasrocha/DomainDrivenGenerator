package com.perfani.core.application;

import java.util.Collection;

import com.perfani.core.application.interfaces.IAppServiceBase;
import com.perfani.core.domain.interfaces.services.IServiceBase;

public class AppServiceBase<T, TT>
       implements IAppServiceBase<T, TT>
{
	private final IServiceBase<T> _serviceBase;
    
    public AppServiceBase(IServiceBase<T> serviceBase)
    {
        _serviceBase = serviceBase;
    }

	@Override
	public boolean add(T obj) 
	{
		return _serviceBase.add(obj);
	}

	@Override
    public boolean add(Collection<T> obj)
    {
		return _serviceBase.add(obj);
    }

	@Override
	public boolean update(T obj) 
	{
		return _serviceBase.update(obj);
	}

	@Override
    public boolean update(Collection<T> obj)
    {
		return _serviceBase.update(obj);
    }
	
	@Override
	public T getById(TT id) 
	{
		return _serviceBase.getById(id);
	}

	@Override
    public Collection<T> getById(Collection<TT> ids)
    {
	    return _serviceBase.getById(ids);
    }
	
	@Override
	public boolean remove(T obj) 
	{
		return _serviceBase.remove(obj);
	}

	@Override
    public boolean remove(Collection<T> obj)
    {
		return _serviceBase.remove(obj);
    }
}
