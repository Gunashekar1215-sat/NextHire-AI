package com.nexthire.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ResumeTextExtractor {

    public static String extractText(File file) throws IOException {

        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".docx")) {
            return extractDocx(file);
        }

        if (fileName.endsWith(".pdf")) {
            return extractPdf(file);
        }

        return "Unsupported File Type";
    }


    private static String extractDocx(File file) throws IOException {

        StringBuilder text = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText());
                text.append("\n");
            }

        }

        return text.toString();
    }


    private static String extractPdf(File file) throws IOException {

        try (PDDocument document = Loader.loadPDF(file)) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);
        }
    }
}