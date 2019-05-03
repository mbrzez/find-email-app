package pl.brzezins.maks.factory;

import pl.brzezins.maks.extractor.*;
import pl.brzezins.maks.utils.FileWrapper;

public class FileExtractorFactory {
    private FileWrapper fileWrapper;

    public FileExtractorFactory(FileWrapper fileWrapper) {
        this.fileWrapper = fileWrapper;
    }

    public FileExtractor create() {
        String extension = fileWrapper.getExtension();

        switch (extension) {
            case "doc":
                return new DocFileExtractor();
            case "docx":
                return new DocxFileExtractor();
            case "pdf":
                return new PdfFileExtractor();
            case "txt":
            default:
                if (fileWrapper.isTextFile()) {
                    return new TextFileExtractor();
                }
        }

        return new UnknownFileExtractor();
    }
}
