package pl.brzezins.maks.extractor;

import pl.brzezins.maks.regex.EmailRegex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TextFileExtractor implements FileExtractor {
    @Override
    public List<String> extract(File file) {
        try (Scanner scanner = new Scanner(file)) {

            List<String> results = new ArrayList<>();

            while (scanner.hasNextLine()) {
                results.addAll(EmailRegex.match(scanner.nextLine()));
            }

            return results;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
