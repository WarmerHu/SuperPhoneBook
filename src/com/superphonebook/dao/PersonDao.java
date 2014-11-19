package com.superphonebook.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.superphonebook.map.BTreeMap;
import com.superphonebook.model.Person;
import com.superphonebook.utils.FileUtil;

public class PersonDao implements IPersonDao{
    private BTreeMap<String,Person> map;

    public PersonDao() {
	this.map = FileUtil.readMap();
    }

    public PersonDao(BTreeMap<String, Person> map) {
	this.map = map;
    }

    /**
     * 通过姓名查询联系人，返回联系人姓名的集合
     */
    public List<String> find(Object key) {
	List<String> persons = new ArrayList<String>();
	Set<String> keySet = map.keySet();
	for(String name : keySet) {
	    if(name.indexOf(key.toString()) >= 0)
		persons.add(name);
	}
	return persons;
    }

    /**
     * 插入联系人
     */
    public void insert(Person p) {
	map.put(p.getName(), p);
	FileUtil.append(p);
    }
    /**
     * 删除联系人
     */
    public void delete(Person p) {
	map.remove(p.getName());
	FileUtil.writeMap(map);
    }
    
    /**
     * 更新联系人数据
     */
    public void update(Person p) {
	map.remove(p.getName());
	map.put(p.getName(), p);
	FileUtil.writeMap(map);
    }
    
    /**
     * 获得所有联系人列表
     */
    public List<Person> findAll() {
	List<Person> persons = new ArrayList<Person>();
	for(String name : map.keySet()) {
	    persons.add(map.get(name));
	}
	return persons;
    }

    /**
     * 通过名字获取联系人，返回联系人
     */
    public Person get(Object key) {
	return map.get(key);
    }

    /**
     * 获得按拼音排序的名称列表
     */
    public List<String> getPinYinNameList() {
	List<String> persons = new ArrayList<String>();
	for(String name : map.keySet()) {
	    persons.add(name);
	}
	return persons;
    }

    /**
     * 查找是否存在该联系人
     */
    public boolean contain(Person p) {
	return map.containsKey(p.getName());
    }

}
