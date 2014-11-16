package com.superphonebook.service;

import java.util.List;

import com.superphonebook.dao.IPersonDao;
import com.superphonebook.dao.PersonDao;
import com.superphonebook.model.Person;

public class PersonService implements IPersonService {
    
    private IPersonDao personDao;
    
    public PersonService() {
	personDao = new PersonDao();
    }

    public List<String> getDefaultNameList() {
	return personDao.getPinYinNameList();
    }

    public void savePerson(Person p) {
	personDao.insert(p);
    }

}
