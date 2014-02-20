package com.jim.demo.jsoup;

import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/17/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DocumentFactory {
    public static AbstractDocumentHandler createDocumentHandler(SourceType sourceType) {
        switch (sourceType) {
            case URL:
                return new URLDocumentHandler();
            case FILE:
                return new FileDocumentHandler();
            case STRING:
                return new StringDocumentHandler();
            case XML:
                return new XMLDocumentHandler();
            default:
                return null;
        }
    }
}
