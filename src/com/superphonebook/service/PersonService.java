package com.superphonebook.service;

import java.util.List;

import com.superphonebook.dao.IPersonDao;
import com.superphonebook.dao.PersonDao;

public class PersonService implements IPersonService {
    
    private IPersonDao personDao;
    
    public PersonService() {
	personDao = new PersonDao();
    }

    public List<String> getDefaultNameList() {
	return personDao.getPinYinNameList();
    }

}
