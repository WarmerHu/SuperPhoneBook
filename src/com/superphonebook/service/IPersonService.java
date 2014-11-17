package com.superphonebook.service;

import java.util.List;

import com.superphonebook.model.Person;

public interface IPersonService {

    List<String> getDefaultNameList();

    void savePerson(Person p);

    Person getPersonByName(String personName);

}
