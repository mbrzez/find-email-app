package pl.brzezins.maks.app;

import pl.brzezins.maks.directory.FileDirectory;
import pl.brzezins.maks.extractor.ContentExtractor;
import pl.brzezins.maks.helpers.ApplicationParams;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Application {

    public static final int NUMBER_OF_THREADS = 4;

    public static void main(String[] args){
        String path = ApplicationParams.getPathFromArgs(args);

        if (path == null) return;

        FileDirectory fileDirectory = new FileDirectory(path);
        List<String> files = fileDirectory.getAllFiles();

        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(files);

        System.out.println("Starting multi-thread processing");

        // Alternative
        // ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        //
        // for (int i = 0; i < NUMBER_OF_THREADS; i++) {
        //    executor.execute(new ContentExtractor(blockingQueue));
        // }
        //
        // executor.shutdown();

        Queue<Thread> threadQueue = new LinkedList<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Runnable runnable = new ContentExtractor(blockingQueue);
            Thread thread = new Thread(runnable);

            threadQueue.add(thread);
            thread.start();
        }

        while (threadQueue.peek() != null) {
            try {
                threadQueue.poll().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads finished their work");
    }
}
