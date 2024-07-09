package com.xuanpt2.slogjava.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    private static final Pattern pattern = Pattern.compile("!\\[(.*?)\\]\\((.*?)\\)",Pattern.CASE_INSENSITIVE);

    public static Set<String> getMdImgUrl(String mdStr){
        Set<String> imgSet = new HashSet<>();

        Matcher matcher = pattern.matcher(mdStr);
        while(matcher.find()){
            String imgUrl = matcher.group(2);
            imgSet.add(imgUrl);
        }
        if (imgSet.isEmpty()) {
            imgSet.add("");
        }
        return imgSet;
    }


}
