package com.xuanpt2.slogjava.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    private static final Pattern PATTERN = Pattern.compile("!\\[(.*?)\\]\\((.*?)\\)",Pattern.CASE_INSENSITIVE);
    private static final String REGEX_STR = "[^a-zA-Z0-9\\u4e00-\\u9fa5]";

    public static Set<String> getMdImgUrl(String mdStr){
        Set<String> imgSet = new HashSet<>();

        Matcher matcher = PATTERN.matcher(mdStr);
        while(matcher.find()){
            String imgUrl = matcher.group(2);
            imgSet.add(imgUrl);
        }
        if (imgSet.isEmpty()) {
            imgSet.add("");
        }
        return imgSet;
    }

    public static String getPureText(String originText){
        return originText.replaceAll(REGEX_STR, "");
    }


}
