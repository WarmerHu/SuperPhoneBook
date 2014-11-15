package com.superphonebook.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.os.Environment;

import com.superphonebook.map.BTreeMap;
import com.superphonebook.map.comparator.PinYinComparator;
import com.superphonebook.model.Person;

public class FileUtil {
    public static final String PATH = Environment.getExternalStorageDirectory()
	    .getAbsolutePath();
    private static String DATA_URL = PATH + "/phonebook.txt";
    
    public static BTreeMap<String,Person> readPerson() {
	BTreeMap<String,Person> map = new BTreeMap<String, Person>(new PinYinComparator());
	InputStreamReader read;
	try {
	    read = new InputStreamReader(
	    	new FileInputStream(new File(DATA_URL)), "UTF-8");
	    BufferedReader reader = new BufferedReader(read);
		String line;
		while ((line = reader.readLine()) != null) {
		    Person p = new Person(line);
		    map.put(p.getName(), p);
		}
	    reader.close();
	    read.close();
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	return map;
	
    }
    
    public static void writeMap(BTreeMap<String,Person> map) {
	FileWriter fw = null;
	
	try {
	    fw = new FileWriter(new File(DATA_URL));
	    for(String name : map.keySet()) {
		fw.write(map.get(name).toString());
	    }
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}finally {
	    try {
		fw.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
