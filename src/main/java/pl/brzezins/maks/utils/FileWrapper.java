package pl.brzezins.maks.utils;

import java.io.*;
import java.nio.file.Files;

public class FileWrapper {
    private File file;

    public FileWrapper(File file) {
        this.file = file;
    }

    public String getExtension() {
        String filename = file.getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);

        return extension;
    }

    public boolean isTextFile() {
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

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }
}