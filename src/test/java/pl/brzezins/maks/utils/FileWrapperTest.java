package pl.brzezins.maks.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileWrapperTest {
    @TempDir
    Path tempDirectory;

    @Test
    void getExtension() {
        File file = tempDirectory.resolve("text.txt").toFile();
        FileWrapper fileWrapper = new FileWrapper(file);

        String extension = fileWrapper.getExtension();

        assertEquals("txt", extension);
    }

    @Test
    void isTextFile() {
        File file = tempDirectory.resolve("text.txt").toFile();
        FileWrapper fileWrapper = new FileWrapper(file);

        boolean textFile = fileWrapper.isTextFile();

        assertTrue(textFile);
    }

    @Test
    void isNotTextFile() {
        File file = tempDirectory.resolve("text.file").toFile();
        FileWrapper fileWrapper = new FileWrapper(file);

        boolean textFile = fileWrapper.isTextFile();

        assertFalse(textFile);
    }
}