package pl.brzezins.maks.extractor;

import pl.brzezins.maks.regex.EmailRegex;
import pl.brzezins.maks.utils.FileWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TextFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(FileWrapper fileWrapper) {
        try (InputStream stream = fileWrapper.getInputStream()) {
            Scanner scanner = new Scanner(stream);
            List<String> results = new ArrayList<>();

            while (scanner.hasNextLine()) {
                results.addAll(EmailRegex.match(scanner.nextLine()));
            }

            return results;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
