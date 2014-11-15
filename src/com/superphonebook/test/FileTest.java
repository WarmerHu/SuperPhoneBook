package com.superphonebook.test;

import android.test.AndroidTestCase;

import com.superphonebook.map.BTreeMap;
import com.superphonebook.map.comparator.PinYinComparator;
import com.superphonebook.model.Person;
import com.superphonebook.utils.FileUtil;

public class FileTest extends AndroidTestCase {
    public void testWrite() throws Exception{
	BTreeMap<String,Person> map = new BTreeMap<String,Person>(new PinYinComparator());
	map.put("蔡大",new Person("蔡大", "111111111111111"));
	map.put("伟二",new Person("伟二", "111111111111111"));
	map.put("龙三",new Person("龙三", "111111111111111"));
	FileUtil.writeMap(map);
    }
    
    public void testRead() throws Exception{
	BTreeMap<String,Person> map = FileUtil.readPerson();
	map.print();
    }
}
