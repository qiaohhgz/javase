package com.jim.pdf;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 10/8/13
 * Time: 5:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class PDFConvert {
    public static void createPDFFromTemplate(String templatePath, String outFilePath) throws Exception {
        PdfReader reader = new PdfReader(templatePath);
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outFilePath));
        try {
            stamp.setFormFlattening(true);
            PdfDictionary pdfDict = (PdfDictionary) reader.getPdfObject(reader
                    .getCatalog().get(PdfName.ACROFORM));
            PdfName pdfName = new PdfName("XFA");
            pdfDict.remove(pdfName);
        } finally {
            if (null != reader) {
                reader.close();
            }
            if (null != stamp) {
                stamp.close();
            }
        }
    }

    private List<Map<String, Object>> fieldMappingList;

    public void createPDF() {
        for (Map<String, Object> fieldMapping : fieldMappingList) {
            Set<String> keySet = fieldMapping.keySet();
            for (String mappingName : keySet) {
                Object pdfField = fieldMapping.get(mappingName);
                MappingName instance = MappingName.getInstance(mappingName);

                List<String> pdfFieldList = new ArrayList<String>();
                if (!(pdfField instanceof List)) {
                    pdfFieldList.add(String.valueOf(pdfField));
                }
                switch (instance) {
                    case requestId: {
                        putRequestId(pdfFieldList);
                        break;
                    }
                    case sampleKeywords: {
                        putSampleKeywords(pdfFieldList);
                        break;
                    }
                    case mapUrl: {
                        putMapUrl(pdfFieldList);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
    }

    private void putRequestId(List<String> pdfField) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void putSampleKeywords(List<String> pdfField) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private void putMapUrl(List<String> pdfField) {
        //To change body of created methods use File | Settings | File Templates.
    }

    public List<Map<String, Object>> getFieldMappingList() {
        return fieldMappingList;
    }

    public void setFieldMappingList(List<Map<String, Object>> fieldMappingList) {
        this.fieldMappingList = fieldMappingList;
    }
}

enum MappingName {
    requestId, sampleKeywords, mapUrl;

    static MappingName getInstance(String name) {
        MappingName[] values = MappingName.values();
        for (MappingName entity : values) {
            if (name.equalsIgnoreCase(entity.name())) {
                return entity;
            }
        }
        return null;
    }

}
