package pl.brzezins.maks.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegex {

    private static final String EMAIL_PATTERN = "(?i)\\b[\\w.%-]+@[-.\\w]+\\.[a-z]{2,4}\\b";

    public static List<String> match(String input) {

        List<String> allMatches = new ArrayList<>();
        Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(input);

        while (matcher.find()) {
            allMatches.add(matcher.group());
        }

        return allMatches;
    }

}
