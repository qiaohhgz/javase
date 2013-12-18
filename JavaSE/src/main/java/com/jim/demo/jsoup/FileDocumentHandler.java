package com.jim.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/17/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileDocumentHandler extends AbstractDocumentHandler {
    @Override
    public Document getDocument() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jsoup-test-data.htm");
        String baseURI = "http://www.cnszdesign.com/";
        Document doc = Jsoup.parse(inputStream, "UTF-8", baseURI);
        return doc;
    }

    @Override
    public String getCssQuery() {
        return super.getCssQuery() + " a[href]";
    }

    @Override
    public void resultHandle(Elements elements) {
        for (Element element : elements) {
            System.out.println(element.outerHtml());
        }
    }
}
