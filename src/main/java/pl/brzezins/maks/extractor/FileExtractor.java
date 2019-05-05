package pl.brzezins.maks.extractor;

import pl.brzezins.maks.utils.FileWrapper;

import java.util.List;

public interface FileExtractor {
    List<String> extract(FileWrapper fileWrapper);
}