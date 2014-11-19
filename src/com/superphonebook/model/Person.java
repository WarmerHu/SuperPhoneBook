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
    public Person(String[] personString) {
	this.setName(personString[0]);
	this.setNumber(personString[1]);
	this.setUnitNumber(StringUtil.isBlank(personString[2])?"#":personString[2]);
	this.setFamilyNumber(StringUtil.isBlank(personString[3])?"#":personString[3]);
	this.setUnitName(StringUtil.isBlank(personString[4])?"#":personString[4]);
	this.setAddress(StringUtil.isBlank(personString[5])?"#":personString[5]);
	this.setEmail(StringUtil.isBlank(personString[6])?"#":personString[6]);
	this.setRemark(StringUtil.isBlank(personString[7])?"#":personString[7]);
    }
    public Person() {
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
	StringBuffer sb = new StringBuffer(getName() + "," + getNumber() + ",");
	sb.append(StringUtil.isBlank(unitNumber)?"#,":unitNumber + ",")
	.append(StringUtil.isBlank(familyNumber)?"#,":familyNumber + ",")
	.append(StringUtil.isBlank(unitName)?"#,":unitName + ",")
	.append(StringUtil.isBlank(address)?"#,":address + ",")
	.append(StringUtil.isBlank(email)?"#,":email + ",")
	.append(StringUtil.isBlank(remark)?"#\n":remark + "\n");
	return sb.toString();
    }
    
    //"#"为占位符，表示为空
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getFamilyNumber() {
        return familyNumber;
    }
    public void setFamilyNumber(String familyNumber) {
        this.familyNumber = familyNumber;
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
