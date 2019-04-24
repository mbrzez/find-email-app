package pl.brzezins.maks.helpers;

public class ApplicationParams {
    public static String getPathFromArgs(String[] args) {
        if (args.length >= 2 && "-p".equals(args[0])) {
            return args[1];
        }

        return null;
    }
}
