package com.jim.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/17/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaiDuNews {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://news.baidu.com").get();
        Elements list = doc.select("ul.ulist li");
        for (Element li : list) {
            System.out.println(li.text());
        }
    }
}
