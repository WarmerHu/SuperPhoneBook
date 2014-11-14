package com.superphonebook.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.superphonebook.dao.PersonDao;
import com.superphonebook.map.BTreeMap;
import com.superphonebook.model.Person;

public class BTreeTest extends AndroidTestCase{
    
    public void maptest() throws Exception {
	BTreeMap<String,Person> bTree = new BTreeMap<String,Person>();
	
	for(int i = 0;i<=64;++i) {
            char a = (char) ('0' + i); 
            for(int j = 64;j >= 0; --j ) {
        	char b = (char) ('0' + j);
        	bTree.put(a + "" + b,new Person( a + "" + b,"1111111111111"));
            }
        }
	PersonDao personDao = new PersonDao(null);
	List<Person> persons = personDao.find("p");
	for(Person p : persons) {
	    Log.d(p.getName(), p.getNumber());
	}
    }

}
