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
    private static File file;
    
    static {
	file = new File(DATA_URL);
	if(!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    /**
     * 读取文件中的数据
     * @return 以map集合储存的数据
     */
    public static BTreeMap<String,Person> readMap() {
	BTreeMap<String,Person> map = new BTreeMap<String, Person>(PinYinComparator.getPinYinComparator());
	InputStreamReader read;
	try {
	    read = new InputStreamReader(
	    	new FileInputStream(file), "UTF-8");
	    BufferedReader reader = new BufferedReader(read);
		String line;
		while ((line = reader.readLine()) != null) {
		    String[] ss = line.split(",");
		    if(ss.length >= 8) {
			Person p = new Person(ss);
			map.put(p.getName(), p);
		    }
		}
	    reader.close();
	    read.close();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	return map;
	
    }
    
    /**
     * 将数据保存进文件中
     * @param map 内存中的数据
     */
    public static void writeMap(BTreeMap<String,Person> map) {
	FileWriter fw = null;
	
	try {
	    fw = new FileWriter(file);
	    for(String name : map.keySet()) {
		fw.write(map.get(name).toString());
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}finally {
	    try {
		fw.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    
    /**
     * 将数据添加到文件的最后一行
     */
    public static void append(Person p) {
	FileWriter fw = null;
	try {
	    fw = new FileWriter(file,true);
	    fw.write(p.toString());
	    fw.flush();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}finally {
	    try {
		fw.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    
}
