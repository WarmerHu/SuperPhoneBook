package com.superphonebook.dao;

import java.util.List;

public interface IDao<T> {
    public T get(Object key);
    public List<T> find(Object key);
    public List<T> findAll();
    public void insert(T t);
    public void delete(T t);
    public void update(T t);
}
