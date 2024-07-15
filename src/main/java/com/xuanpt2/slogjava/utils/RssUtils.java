package com.xuanpt2.slogjava.utils;


import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

public class RssUtils {
//    public static SyndFeed test(){
//        String url = "https://2heng.xin/feed";
//        try {
//            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
//            System.out.println(feed.toString());
//            return feed;
//        }catch (Exception e){
//            System.out.println(e.toString());
//            return null;
//        }
//
//    }

    public static SyndFeed parse(String url){
        try {
            //System.out.println(feed.toString());
            return new SyndFeedInput().build(new XmlReader(new URL(url)));
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
}
