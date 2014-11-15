package com.superphonebook.test;

import java.util.List;

import com.superphonebook.dao.PersonDao;
import com.superphonebook.map.BTreeMap;
import com.superphonebook.map.comparator.PinYinComparator;
import com.superphonebook.model.Person;

import android.test.AndroidTestCase;
import android.util.Log;

public class DaoTest extends AndroidTestCase {
    
//    public void testFind() throws Exception{
//	BTreeMap<String,Person> bTree = new BTreeMap<String,Person>();
//	for(int i = 0;i<=64;++i) {
//            char a = (char) ('0' + i); 
//            for(int j = 64;j >= 0; --j ) {
//        	char b = (char) ('0' + j);
//        	bTree.put(a + "" + b,new Person( a + "" + b,"1111111111111"));
//            }
//        }
//	PersonDao personDao = new PersonDao(bTree);
//	List<Person> persons = personDao.find("p");
//	for(Person p : persons) {
//	    Log.d(p.getName(), p.getNumber());
//	}
//    }
    
    public void testWrite() throws Exception{
	BTreeMap<String,Person> bTree = new BTreeMap<String,Person>(new PinYinComparator());
	PersonDao personDao = new PersonDao(bTree);
	personDao.insert(new Person("蔡大", "111111111111111"));
	personDao.insert(new Person("伟二", "111111111111111"));
	personDao.insert(new Person("龙三", "111111111111111"));
	personDao.getMap().print();
    }
    
    public void testRead() throws Exception{
	PersonDao dao = new PersonDao();
	dao.getMap().print();
    }
}
