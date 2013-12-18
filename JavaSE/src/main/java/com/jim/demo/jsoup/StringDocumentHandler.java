package com.jim.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/17/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringDocumentHandler extends AbstractDocumentHandler {
    @Override
    public Document getDocument() throws IOException {
        StringBuffer html = new StringBuffer();
        html.append("<div id='fontsize_6562'>");
        html.append("<p>What are you trying to say?（你到底想说什么？）</p>");
        html.append("<p>Don't be silly.（别胡闹了。）</p>");
        html.append("<p>How strong are your glasses?（你近视多少度？）</p>");
        html.append("<p>Just because.（没有别的原因。）</p>");
        html.append("</div>");
        Document doc = Jsoup.parse(html.toString());
        return doc;
    }
}
