package com.hvktmm.at12i.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class test {
//    public static final String RESULT = "results/part1/chapter05/hero3.pdf";
//
//    /**
//     * Creates a PDF document.
//     * @param filename the path to the new PDF document
//     * @throws    DocumentException
//     * @throws    IOException
//     */
//    public void createPdf(String filename) throws IOException, DocumentException {
//        // step 1
//        Document document = new Document(PageSize.A4);
//        // step 2
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
//        Rectangle art = new Rectangle(50, 50, 545, 792);
//        writer.setBoxSize("art", art);
//        // step 3
//        document.open();
//        // step 4
//        PdfContentByte content = writer.getDirectContent();
//        PdfTemplate template = createTemplate(content, PageSize.A4, 1);
//        content.addTemplate(template, 0, 0);
//        // step 5
//        document.close();
//    }
//
//    /**
//     * Main method.
//     * @param    args    no arguments needed
//     * @throws DocumentException
//     * @throws IOException
//     */
//    public static void main(String[] args)
//            throws IOException, DocumentException {
//        new Hero3().createPdf(RESULT);
//    }
}
