package pl.brzezins.maks.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import pl.brzezins.maks.regex.EmailRegex;
import pl.brzezins.maks.utils.FileWrapper;

public class DocxFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(FileWrapper fileWrapper) {
        try (InputStream stream = fileWrapper.getInputStream();
             XWPFDocument document = new XWPFDocument(stream);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {

            return EmailRegex.match(extractor.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
