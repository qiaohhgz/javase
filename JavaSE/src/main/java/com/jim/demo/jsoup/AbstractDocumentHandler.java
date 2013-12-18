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
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDocumentHandler {
    public abstract Document getDocument() throws IOException;

    public void execute() throws IOException {
        Document doc = getDocument();
        Elements newsHeadlines = doc.select(getCssQuery());
        resultHandle(newsHeadlines);
    }

    public String getCssQuery() {
        return "#fontsize_6562 p";
    }

    public void resultHandle(Elements elements) {
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}
