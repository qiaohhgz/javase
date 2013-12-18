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
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.cnszdesign.com/Article_Show.asp?ArticleID=2834").get();
        Elements newsHeadlines = doc.select("#fontsize_6562 p");
        for (Element element : newsHeadlines) {
            System.out.println(element.text());
        }

        DocumentFactory.createDocumentHandler(SourceType.URL).execute();
        DocumentFactory.createDocumentHandler(SourceType.STRING).execute();
        DocumentFactory.createDocumentHandler(SourceType.FILE).execute();

    }
}
