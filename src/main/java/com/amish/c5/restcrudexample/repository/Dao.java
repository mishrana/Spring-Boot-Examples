package com.amish.c5.restcrudexample.repository;

import java.util.List;

public interface Dao<T> {

	public List<T> findAll();
	
	public T find(Long id);
	
	public T create(T record, Long id);
	
	public T update(T record, Long id);
	
	public Long delete(Long id);
}
