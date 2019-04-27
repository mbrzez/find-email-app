package pl.brzezins.maks.factory;

import pl.brzezins.maks.extractor.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileExtractorFactory {
    private FileExtractorFactory() {}

    public static FileExtractor create(File file) {
        String extension = getExtension(file);

        switch (extension) {
            case "pdf":
                return new PdfFileExtractor();
            case "doc":
                return new DocFileExtractor();
            case "docx":
                return new DocxFileExtractor();
            case "txt":
            default:
                if (isTextFile(file)) {
                    return new TextFileExtractor();
                }
        }

        return new UnknownFileExtractor();
    }

    private static String getExtension(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        return extension;
    }

    private static boolean isTextFile(File file) {
        try {
            String mimeType = Files.probeContentType(file.toPath());

            if (mimeType != null && mimeType.contains("text")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
