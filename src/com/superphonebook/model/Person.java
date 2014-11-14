package com.superphonebook.model;

import java.io.Serializable;

public class Person implements Serializable{
    
    private static final long serialVersionUID = -5684798839690583880L;
    
    private String name;
    private String number;
    public Person(String name, String number) {
	this.name = name;
	this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}