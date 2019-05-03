package pl.brzezins.maks.extractor;

import pl.brzezins.maks.utils.FileWrapper;

import java.util.Collections;
import java.util.List;

public class UnknownFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(FileWrapper fileWrapper) {
        return Collections.emptyList();
    }
}
