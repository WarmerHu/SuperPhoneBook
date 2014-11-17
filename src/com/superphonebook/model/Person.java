package com.superphonebook.model;

import java.io.Serializable;

import com.superphonebook.utils.StringUtil;

public class Person implements Serializable{
    
    private static final long serialVersionUID = -5684798839690583880L;
    
    private String name;
    private String number;
    private String unitNumber;
    private String familyNumber;
    private String unitName;
    private String address;
    private String email;
    private String remark;
   
    public Person(String name, String number) {
	this.name = name;
	this.number = number;
    }
    public Person(String line) {
	String personString[] = line.split(",");
	this.setName(personString[0]);
	this.setNumber(personString[1]);
	if(personString.length <= 2 ) 
	    return ;
	this.setUnitNumber(personString[2]);
	this.setFamilyNumber(personString[3]);
	this.setUnitName(personString[4]);
	this.setAddress(personString[5]);
	this.setEmail(personString[6]);
	this.setRemark(personString[7]);
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
    public String toString() {
	return (getName() + "," + 
		getNumber() + "," + 
		getUnitNumber() + "," + 
		getFamilyNumber() + "," +
		getUnitName() + "," + 
		getAddress() + "," + 
		getEmail() + "," + 
		getRemark() + "," + 
		"\n");
    }
    
    //"#"为占位符，表示为空
    public String getUnitNumber() {
        return StringUtil.isBlank(unitNumber)?"#":unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getFamilyNumber() {
        return StringUtil.isBlank(familyNumber)?"#":familyNumber;
    }
    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }
    public String getUnitName() {
        return StringUtil.isBlank(unitName)?"#":unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getAddress() {
        return StringUtil.isBlank(address)?"#":address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return StringUtil.isBlank(email)?"#":email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRemark() {
        return StringUtil.isBlank(remark)?"#":remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
