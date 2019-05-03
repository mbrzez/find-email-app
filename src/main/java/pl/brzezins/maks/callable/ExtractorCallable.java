package pl.brzezins.maks.callable;

import pl.brzezins.maks.extractor.FileExtractor;
import pl.brzezins.maks.logging.ExtractorLogging;
import pl.brzezins.maks.utils.FileWrapper;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class ExtractorCallable extends ExtractorLogging implements Callable<List<String>> {
    private FileWrapper fileWrapper;
    private FileExtractor fileExtractor;

    public ExtractorCallable(FileWrapper fileStreamer, FileExtractor fileExtractor) {
        this.fileWrapper = fileStreamer;
        this.fileExtractor = fileExtractor;
    }

    public List<String> call() {
        if (fileExtractor == null) {
            return Collections.emptyList();
        }

        return fileExtractor.extract(fileWrapper);
    }
}
