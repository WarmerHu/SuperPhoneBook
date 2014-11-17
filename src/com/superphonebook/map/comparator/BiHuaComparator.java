package com.superphonebook.map.comparator;
import java.util.Comparator;
import java.util.Properties;
import java.util.Scanner;

//笔画数比较器
public class BiHuaComparator implements Comparator<String> {
    
    private BiHuaComparator() {
    }
    private static BiHuaComparator biHuaComparator;
    public static BiHuaComparator getBiHuaComparator() {
	if(biHuaComparator == null) {
	    biHuaComparator = new BiHuaComparator();
	}
	return biHuaComparator;
    }

    public int compare(String arg0, String arg1) {
	int codepoint1 = 0;
	int codepoint2 = 0;

	for (int i = 0; i < arg0.length() && i < arg1.length(); i++) {
	    codepoint1 = arg0.codePointAt(i);
	    codepoint2 = arg1.codePointAt(i);

	    if (codepoint1 == codepoint2) {
		continue;
	    }
	    int stroke1 = Chinese.stroke(codepoint1);
            int stroke2 = Chinese.stroke(codepoint2);
            if (stroke1 < 0 || stroke2 < 0) {
                return codepoint1 - codepoint2;
            }
            if (stroke1 != stroke2) {
                return stroke1 - stroke2;
            }
	}
	return arg0.length() - arg1.length();
    }

}

class Chinese {
    private static Properties strokesMap = new Properties();

    static {
	@SuppressWarnings("resource")
	Scanner in = new Scanner(
		Chinese.class.getResourceAsStream("Stroke.csv"));
	String temp;

	int i = 19968;

	while (in.hasNextLine()) {
	    temp = in.nextLine();
	    strokesMap.setProperty((i++) + "", temp);
	}
    }

    public static int stroke(int codepoint) {
	String result = strokesMap.getProperty(codepoint + "");
	if (result == null)
	    result = "-1";
	return Integer.valueOf(result);

    }
}
