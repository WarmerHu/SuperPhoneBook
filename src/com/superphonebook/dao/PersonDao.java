package com.superphonebook.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.os.Environment;

import com.superphonebook.map.BTreeMap;
import com.superphonebook.map.comparator.PinYinComparator;
import com.superphonebook.model.Person;

public class PersonDao implements IDao<Person>{
    private BTreeMap<String,Person> map;
    public static final String PATH = Environment.getExternalStorageDirectory()
	    .getAbsolutePath();
    private static String DATA_URL = PATH + "/phonebook.txt";
    
    public BTreeMap<String, Person> getMap() {
        return map;
    }

    public void setMap(BTreeMap<String, Person> map) {
        this.map = map;
    }

    public PersonDao() {
	readPerson();
    }
    
    public void readPerson() {
	ObjectInputStream objectInputStream = null;
	try {
	    objectInputStream = new ObjectInputStream(
		    		new FileInputStream(
		    		new File(DATA_URL)));
	    if (map == null) {
		map = new BTreeMap<String, Person>(new PinYinComparator());
	    }
	    Object[] persons = (Object[]) objectInputStream.readObject();
	    System.out.println(persons);
	    for(Object p : persons) {
		insert((Person)p);
	    }
	} catch (StreamCorruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}finally {
	    try {
		objectInputStream.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
    
    public void writePerson() {
	ObjectOutputStream objectOutputStream = null;
	try {
	    objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(DATA_URL)));
	    objectOutputStream.writeObject(map.values().toArray());
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}finally {
	    try {
		objectOutputStream.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    public PersonDao(BTreeMap<String, Person> map) {
	this.map = map;
    }

    /**
     * 通过姓名查询联系人，返回联系人的集合
     * 
     * @author Wealong
     */
    public List<Person> find(Object key) {
	String regex = (String) key + ".+";
	List<Person> persons = new ArrayList<Person>();
	Set<String> keySet = map.keySet();
	for(String name : keySet) {
	    if(name.matches(regex))
		persons.add(map.get(name));
	}
	return persons;
    }

    public void insert(Person p) {
	map.put(p.getName(), p);
    }

    public void delete(Person p) {
	// TODO Auto-generated method stub
	
    }

    public void update(Person p) {
	// TODO Auto-generated method stub
	
    }

    public List<Person> findAll() {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * 通过名字获取联系人，返回联系人
     * 
     * @author Wealong
     */
    public Person get(Object key) {
	return map.get(key);
    }
}