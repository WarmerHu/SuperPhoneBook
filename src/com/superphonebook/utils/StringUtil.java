package com.superphonebook.utils;

public class StringUtil {
    public static boolean isBlank(String s) {
	int strLen = 0;
	if(s == null || ((strLen = s.length()) == 0 || "#".equals(s)) )
	    return true;
	for(int i = 0; i < strLen; i++) {
	    if(Character.isWhitespace(s.charAt(i)) == false) {
		return false;
	    }
	}
	return true;
    }
    
    public static boolean isNotBlank(String s) {
	return !isBlank(s);
    }
}
