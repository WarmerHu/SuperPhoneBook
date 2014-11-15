package com.superphonebook.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.superphonebook.dao.PersonDao;
import com.superphonebook.map.BTreeMap;
import com.superphonebook.model.Person;

public class DaoTest extends AndroidTestCase {
    
    public void testFind() throws Exception{
	BTreeMap<String,Person> bTree = new BTreeMap<String,Person>();
	for(int i = 0;i<=64;++i) {
            char a = (char) ('0' + i); 
            for(int j = 64;j >= 0; --j ) {
        	char b = (char) ('0' + j);
        	bTree.put(a + "" + b,new Person( a + "" + b,"1111111111111"));
            }
        }
	PersonDao personDao = new PersonDao(bTree);
	List<Person> persons = personDao.find("p");
	for(Person p : persons) {
	    Log.d(p.getName(), p.getNumber());
	}
    }
    
    public void testFindAll() throws Exception{
	PersonDao personDao = new PersonDao();
	System.out.println(personDao.findAll());
    }
    
    public void testInsert()throws Exception{
	PersonDao personDao = new PersonDao();
	personDao.insert(new Person("王一","22222222222"));
	System.out.println(personDao.findAll());
    }
}
