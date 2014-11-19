package com.superphonebook.service;

import java.util.List;

import com.superphonebook.dao.IPersonDao;
import com.superphonebook.dao.PersonDao;
import com.superphonebook.model.Person;
import com.superphonebook.utils.StringUtil;

public class PersonService implements IPersonService {
    
    private IPersonDao personDao;
    
    public PersonService() {
	personDao = new PersonDao();
    }

    public List<String> getDefaultNameList() {
	return personDao.getPinYinNameList();
    }

    public void save(Person p) {
	personDao.insert(p);
    }

    public Person getPersonByName(String personName) {
	return personDao.get(personName);
    }

    public List<String> findNameListbyString(String s) {
	if(StringUtil.isNotBlank(s))
	    return personDao.find(s);
	else return getDefaultNameList();
    }

    public void delete(String personName) {
	personDao.delete(getPersonByName(personName));
    }

    public void saveOrUpdatePerson(Person p) {
	if(personDao.contain(p)) {
	    update(p);
	}else {
	    save(p);
	}
    }

    public void update(Person p) {
	personDao.update(p);
    }

}
