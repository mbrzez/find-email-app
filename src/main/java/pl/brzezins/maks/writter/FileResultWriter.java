package pl.brzezins.maks.writter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileResultWriter {
    public static void writeToFile(String filename, List<String> list) {
        File file = new File(filename);

        if (file.isDirectory()) {
            filename += (File.separator + "result.txt");
        }

        try {
            Files.write(Paths.get(filename), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
