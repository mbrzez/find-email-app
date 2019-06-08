package pl.brzezins.maks.helpers;

import java.util.Arrays;
import java.util.List;

public class ApplicationParams {
    public static String getInputDirectory(String[] args) {
        return getArgValue(args, "-i");
    }

    public static String getOutputDirectory(String[] args) {
        return getArgValue(args, "-o");
    }

    private static String getArgValue(String[] args, String arg) {
        List<String> list = Arrays.asList(args);
        int index = list.indexOf(arg);

        if (++index <= list.size()) {
            return list.get(index);
        }

        return null;
    }
}
