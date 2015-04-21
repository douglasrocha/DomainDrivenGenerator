package com.perfani.core.application.interfaces;

import java.util.Collection;

public interface IAppServiceBase<T, TT>
{
	boolean add(T obj);
    
    boolean add(Collection<T> obj);
    
    boolean update(T obj);
    
    boolean update(Collection<T> obj);
    
    T getById(TT id);
    
    Collection<T> getById(Collection<TT> ids);
    
    boolean remove(T obj);
    
    boolean remove(Collection<T> obj);
}
