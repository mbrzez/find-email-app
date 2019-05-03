package pl.brzezins.maks.extractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import pl.brzezins.maks.regex.EmailRegex;
import pl.brzezins.maks.utils.FileWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class PdfFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(FileWrapper fileWrapper) {
        try (InputStream stream = fileWrapper.getInputStream();
             PDDocument document = PDDocument.load(stream)) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            return EmailRegex.match(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
