package pl.brzezins.maks.app;

import pl.brzezins.maks.directory.FileDirectory;
import pl.brzezins.maks.callable.ExtractorCallable;
import pl.brzezins.maks.helpers.ApplicationParams;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Application {

    public static final int NUMBER_OF_THREADS = 4;

    public static void main(String[] args) {
        String sourceDirectory = ApplicationParams.getPathFromArgs(args);

        if (sourceDirectory == null) return;

        FileDirectory fileDirectory = new FileDirectory(sourceDirectory);
        List<Path> paths = fileDirectory.getAllFilesPath();

        System.out.println("Starting multi-thread processing");

        List<Future<List<String>>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (Path path : paths) {
            Future<List<String>> future = executorService.submit(new ExtractorCallable(path.toFile()));
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

        List<String> uniqueAddresses = emailAddresses.stream().distinct().collect(Collectors.toList());

        for (String email : uniqueAddresses) {
            System.out.println("Email address: " + email);
        }

        System.out.println("All threads finished their work");
    }
}
