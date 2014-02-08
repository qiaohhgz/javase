package com.jim.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/23/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class GoogleTrends {

    public static void main(String[] args) throws IOException {
        String keyword = "%E7%88%B8%E7%88%B8%E5%8E%BB%E5%93%AA,+%E9%9B%BE%E9%9C%BE,+security+cameras";
        StringBuffer buffer = new StringBuffer();
        buffer.append("http://www.google.com/trends/embed.js?hl=zh-CN&date=today+1-m&cmpt=q&content=1&cid=TOP_QUERIES_0_0&export=5&w=300&h=420");
        buffer.append("&q=");
        buffer.append(keyword);
        Element body = Jsoup.connect(buffer.toString()).get().body();
        System.out.println(body.outerHtml());

//        Element body1 = Jsoup.connect("http://www.google.com/trends/fetchComponent?hl\\75zh-CN\\46date\\75today+1-m\\46cmpt\\75q\\46content\\0751\\46cid\\75TOP_QUERIES_0_0\\46export\\0755\\46w\\075300\\46h\\075420\\46q\\75%E7%88%B8%E7%88%B8%E5%8E%BB%E5%93%AA,+%E9%9B%BE%E9%9C%BE,+security+cameras").get().body();
//        System.out.println(body1.outerHtml());
    }
}
