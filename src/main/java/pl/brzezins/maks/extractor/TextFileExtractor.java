package pl.brzezins.maks.extractor;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class TextFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(File file) {
        return Collections.emptyList();
    }
}
