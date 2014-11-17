package com.superphonebook.dao;

import java.util.List;

import com.superphonebook.model.Person;

public interface IPersonDao {
    public Person get(Object key);
    public List<String> find(Object key);
    public List<Person> findAll();
    public void insert(Person t);
    public void delete(Person t);
    public void update(Person t);
    public List<String> getPinYinNameList();
}
