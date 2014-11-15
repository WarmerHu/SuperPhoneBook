package com.superphonebook.test;

import android.test.AndroidTestCase;

import com.superphonebook.map.BTreeMap;
import com.superphonebook.model.Person;

public class BTreeTest extends AndroidTestCase{
    
    public BTreeTest() {
	super();
    }
    
    public void testPut() throws Exception {
	BTreeMap<String,Person> bTree = new BTreeMap<String,Person>();
	for(int i = 0;i<=64;++i) {
            char a = (char) ('0' + i); 
            for(int j = 64;j >= 0; --j ) {
        	char b = (char) ('0' + j);
        	bTree.put(a + "" + b,new Person( a + "" + b,"1111111111111"));
            }
        }
	bTree.print();
    }
}
