package pl.brzezins.maks.extractor;

import java.util.concurrent.BlockingQueue;

public class ContentExtractor implements Runnable {
    private BlockingQueue<String> files;

    public ContentExtractor(BlockingQueue<String> files) {
        this.files = files;
    }

    private String getThreadDetails() {
        return getThreadDetails("");
    }

    private String getThreadDetails(String message) {
        if (message.isEmpty()) {
            return "Thread name: " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId();
        }

        return "Thread name: " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId() + " message: " + message;
    }

    @Override
    public void run() {
        System.out.println(this.getThreadDetails("started"));

        while (files.peek() != null) {
            String file = files.poll();

            // TODO Open a file, search for a content and save to synchronized set
            System.out.println(this.getThreadDetails(file));
        }

        System.out.println(this.getThreadDetails("No more elements to process!"));
    }
}
