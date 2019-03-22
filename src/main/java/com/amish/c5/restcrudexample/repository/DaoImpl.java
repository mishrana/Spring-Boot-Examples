package com.amish.c5.restcrudexample.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl<T> implements Dao<T> {
	
	private Map<Long, T> database = new HashMap<>();
	
	@Override
	public Long delete(Long uniqueId) {
		database.remove(uniqueId);
		return uniqueId;
	}

	@Override
	public List<T> findAll() {
		return database.values().stream().collect(Collectors.toList());
	}

	@Override
	public T find(Long uniqueId) {
		
		return database.get(uniqueId);
	}

	@Override
	public T create(T record, Long id) {
		database.put(id, record);
		return record;
	}
	
	@Override
	public T update(T record, Long id) {
		T t = database.get(id);
		if (t == null) {
			return null;
		}
		database.put(id, record);
		return record;
	}
}
