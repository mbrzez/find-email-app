package pl.brzezins.maks.callable;

import pl.brzezins.maks.extractor.FileExtractor;
import pl.brzezins.maks.factory.FileExtractorFactory;
import pl.brzezins.maks.logging.ExtractorLogging;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class ExtractorCallable extends ExtractorLogging implements Callable<List<String>> {

    private File file;

    public ExtractorCallable(File file) {
        this.file = file;
    }

    public List<String> call() {
        //System.out.println(this.getThreadDetails("started!"));
        System.out.println(this.getThreadDetails(file.toString()));

        FileExtractor fileExtractor = FileExtractorFactory.create(file);

        if (fileExtractor == null) {
            return Collections.emptyList();
        }

        List<String> result =  fileExtractor.extract(file);

        //System.out.println(this.getThreadDetails("finished!"));

        return result;
    }
}
