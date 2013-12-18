package com.jim.pdf.first;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 10/8/13
 * Time: 5:55 AM
 * To change this template use File | Settings | File Templates.
 */
class ReadAndUsePdf {
    private static String INPUT_FILE = "FirstPdf.pdf";
    private static String OUTPUT_FILE = "ReadPdf.pdf";

    public static void main(String[] args) throws DocumentException,
            IOException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(OUTPUT_FILE));
        document.open();
        PdfReader reader = new PdfReader(INPUT_FILE);
        int n = reader.getNumberOfPages();
        PdfImportedPage page;
        // Go through all pages
        for (int i = 1; i <= n; i++) {
            // Only page number 2 will be included
            if (i == 2) {
                page = writer.getImportedPage(reader, i);
                Image instance = Image.getInstance(page);
                document.add(instance);
            }
        }
        document.close();

    }
}
