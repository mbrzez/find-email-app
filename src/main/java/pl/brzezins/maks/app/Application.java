package pl.brzezins.maks.app;

import pl.brzezins.maks.callable.ExtractorCallable;
import pl.brzezins.maks.directory.FileDirectory;
import pl.brzezins.maks.factory.FileExtractorFactory;
import pl.brzezins.maks.helpers.ApplicationParams;
import pl.brzezins.maks.utils.FileWrapper;
import pl.brzezins.maks.writter.FileResultWriter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

    public static final int NUMBER_OF_THREADS = 4;

    public static void main(String[] args) {
        String sourcePath = ApplicationParams.getInputDirectory(args);
        String destinationFilename = ApplicationParams.getOutputDirectory(args);

        if (sourcePath == null || destinationFilename == null) {
            System.out.println("Please provide -i and -o params");
            return;
        }

        FileDirectory fileDirectory = new FileDirectory(sourcePath);
        List<Path> paths = fileDirectory.getAllFilesPath();

        List<Future<List<String>>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (Path path : paths) {
            FileWrapper wrapper = new FileWrapper(path.toFile());
            FileExtractorFactory factory = new FileExtractorFactory(wrapper);
            Future<List<String>> future = executorService.submit(new ExtractorCallable(wrapper, factory.create()));
            futures.add(future);
        }

        List<String> emailAddresses = new ArrayList<>();

        for (Future<List<String>> future : futures) {
            try {
                emailAddresses.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        FileResultWriter.writeToFile(destinationFilename, emailAddresses);
    }
}
