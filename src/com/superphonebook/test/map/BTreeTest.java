package com.superphonebook.test.map;

import junit.framework.TestCase;

import org.junit.Test;

import com.superphonebook.map.BTreeMap;

public class BTreeTest extends TestCase {

    @Test
    public void insertTest() {
	BTreeMap<String,String> bTree = new BTreeMap<String,String>();
	
	for(int i = 0;i<=64;++i) {
            char a = (char) ('0' + i); 
            for(int j = 64;j >= 0; --j ) {
        	char b = (char) ('0' + j);
        	bTree.put(a + "" + b, a + "" + b);
            }
        }
    }
}
