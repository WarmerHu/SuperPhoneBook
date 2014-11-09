package com.superphonebook.map.comparator;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

//Æ´Òô±È½ÏÆ÷
public class PinYinComparator implements Comparator<String> {
    
    RuleBasedCollator collator = (RuleBasedCollator)Collator.getInstance(Locale.CHINA);

    public int compare(String arg0, String arg1) {
	return collator.compare(arg0, arg1);
    }

}
