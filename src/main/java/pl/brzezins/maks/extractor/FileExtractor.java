package pl.brzezins.maks.extractor;

import java.io.File;
import java.util.List;

public interface FileExtractor {
    public List<String> extract(File file);
}
