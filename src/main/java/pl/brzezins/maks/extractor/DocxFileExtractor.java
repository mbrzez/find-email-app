package pl.brzezins.maks.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import pl.brzezins.maks.regex.EmailRegex;

public class DocxFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(File file) {
        try (FileInputStream stream = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(stream);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

            return EmailRegex.match(extractor.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
