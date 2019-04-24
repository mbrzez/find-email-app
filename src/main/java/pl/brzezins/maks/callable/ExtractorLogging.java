package pl.brzezins.maks.callable;

public abstract class ExtractorLogging {
    protected String getThreadDetails() {
        return getThreadDetails("");
    }

    protected String getThreadDetails(String message) {
        if (message.isEmpty()) {
            return "Thread name: " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId();
        }

        return "Thread name: " + Thread.currentThread().getName() + " id: " + Thread.currentThread().getId() + " message: " + message;
    }
}
