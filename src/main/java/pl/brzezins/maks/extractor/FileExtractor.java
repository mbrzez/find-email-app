package pl.brzezins.maks.extractor;

import java.io.File;
import java.util.List;

public interface FileExtractor {
    List<String> extract(File file);
}
