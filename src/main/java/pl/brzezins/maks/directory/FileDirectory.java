package pl.brzezins.maks.directory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDirectory {
    private String path;

    public FileDirectory(String path) {
        this.path = path;
    }

    public List<Path> getAllFilesPath() {
        try (Stream<Path> paths = Files.walk(Paths.get(this.path))) {
            return paths.filter((p) -> Files.isRegularFile(p)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
