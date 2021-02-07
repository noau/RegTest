package io.noau.regtest.util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    
    private StringUtils() {}
    
    public static String stringArrayToString(String[] strArr) {
        String result = "[";
        int resLen = strArr.length;
        for (int i = 0; i < resLen; i++) {
            result += "\"" + strArr[i] + "\"";
            if (i < resLen - 1) result += ",\n";
        }
        result += "]";
        return result;
    }
    
    public static String[] searchString(String pattern, String text) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            String subStr = text.substring(matcher.start(), matcher.end());
            result.add(matcher.start() + "   " + subStr);
        }
        return result.toArray(new String[] {});
    }
}
