package com.superphonebook.service;

import java.util.List;

import com.superphonebook.model.Person;

public interface IPersonService {

    List<String> getDefaultNameList();
    void save(Person p);
    void update(Person p);
    Person getPersonByName(String personName);
    List<String> findNameListbyString(String s);
    void delete(String string);
    void saveOrUpdatePerson(Person p);

}
