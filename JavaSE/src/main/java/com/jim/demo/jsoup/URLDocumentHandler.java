package com.jim.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/17/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class URLDocumentHandler extends AbstractDocumentHandler {
    @Override
    public Document getDocument() throws IOException {
        return Jsoup.connect("http://www.cnszdesign.com/Article_Show.asp?ArticleID=2834").get();
    }
}
