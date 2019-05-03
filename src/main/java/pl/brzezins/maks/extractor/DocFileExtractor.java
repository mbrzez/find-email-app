package pl.brzezins.maks.extractor;

import org.apache.poi.hwpf.HWPFDocument;
import pl.brzezins.maks.regex.EmailRegex;
import pl.brzezins.maks.utils.FileWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class DocFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(FileWrapper fileWrapper) {
        try (InputStream stream = fileWrapper.getInputStream();
             HWPFDocument document = new HWPFDocument(stream)) {
            return EmailRegex.match(document.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
