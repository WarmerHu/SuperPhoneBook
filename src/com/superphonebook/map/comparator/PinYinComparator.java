package com.superphonebook.map.comparator;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

//拼音比较器
public class PinYinComparator implements Comparator<String> {
    
    private static PinYinComparator pinYinComparator;
    
    private PinYinComparator() {
    }
    
    public static PinYinComparator getPinYinComparator() {
	if(pinYinComparator == null) {
	    pinYinComparator = new PinYinComparator();
	}
	return pinYinComparator;
    }
    
    RuleBasedCollator collator = (RuleBasedCollator)Collator.getInstance(Locale.CHINA);

    public int compare(String arg0, String arg1) {
	return collator.compare(arg0, arg1);
    }

}
