package pl.brzezins.maks.extractor;

import org.apache.poi.hwpf.HWPFDocument;
import pl.brzezins.maks.regex.EmailRegex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class DocFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(File file) {
        try (FileInputStream stream = new FileInputStream(file);
             HWPFDocument document = new HWPFDocument(stream)) {

            return EmailRegex.match(document.getText().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
