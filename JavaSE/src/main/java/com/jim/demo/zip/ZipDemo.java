package com.jim.demo.zip;

import com.jim.demo.xml.ElementFilter;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipDemo {
    private static final Logger log = Logger.getLogger(ZipDemo.class);

    public void reader(String fileName) throws IOException, DocumentException {
        ZipFile zf = new ZipFile(fileName);
        ZipEntry sheetZipEntry = null;
        Enumeration<? extends ZipEntry> files = zf.entries();
        while (files.hasMoreElements()) {
            ZipEntry zipEntry = files.nextElement();
            if (zipEntry.getName().endsWith("sheet1.xml")) {
                sheetZipEntry = zipEntry;
                break;
            }
        }
        if (sheetZipEntry != null) {
            InputStream in = zf.getInputStream(sheetZipEntry);
            SAXReader sax = new SAXReader();
            Document doc = sax.read(in);
            Element root = doc.getRootElement();
            Element sheet = getSheetData(root, new ElementFilter() {
                @Override
                public boolean accept(Element element) {
                    return element.getName().equalsIgnoreCase("sheetData");
                }
            });
            List<Element> rows = sheet.elements();
            for (Element row : rows) {
                StringBuffer rowValue = new StringBuffer();
                List<Element> cells = row.elements();
                for (Element cell : cells) {
                    cell = (Element) cell.elementIterator().next();
                    cell = (Element) cell.elementIterator().next();
                    rowValue.append(cell.getText());
                    rowValue.append(" ");
                }
                log.info(rowValue.toString());
            }
        }
    }

    private Element getSheetData(Element ele, ElementFilter filter) {
        Element ne = null;
        if (filter.accept(ele)) {
            return ele;
        }
        List<Element> elements = ele.elements();
        for (Element e : elements) {
            Element sheetData = getSheetData(e, filter);
            if (sheetData != null) {
                ne = sheetData;
                break;
            }
        }
        return ne;
    }

    private void readerUseByte(InputStream in) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(in);
        byte[] bs = new byte[1024];
        int length = 0;
        while ((length = bis.read(bs)) != -1) {
            String s = new String(bs, 0, length);
            log.info("s = " + s);
        }
    }
}
