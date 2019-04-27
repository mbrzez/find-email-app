package pl.brzezins.maks.extractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import pl.brzezins.maks.regex.EmailRegex;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PdfFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(File file) {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            return EmailRegex.match(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
