package pl.brzezins.maks.factory;

import pl.brzezins.maks.extractor.FileExtractor;
import pl.brzezins.maks.extractor.PdfFileExtractor;
import pl.brzezins.maks.extractor.TextFileExtractor;
import pl.brzezins.maks.extractor.WordFileExtractor;

import java.io.File;

public class FileExtractorFactory {
    private FileExtractorFactory() {}

    public static FileExtractor create(File file) {
        if (isPdfFile(file)) {
            return new PdfFileExtractor();
        } else if (isWordFile(file)) {
            return new WordFileExtractor();
        } else if (isTextFile(file)) {
            return new TextFileExtractor();
        }

        return null;
    }

    private static boolean isPdfFile(File file) {
        return false;
    }

    private static boolean isWordFile(File file) {
        return false;
    }

    private static boolean isTextFile(File file) {
        return false;
    }
}
