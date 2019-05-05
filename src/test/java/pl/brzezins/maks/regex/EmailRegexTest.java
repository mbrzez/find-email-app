package pl.brzezins.maks.regex;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailRegexTest {

    private static Stream<Arguments> provideArgumentsForEmailRegex() {
        return Stream.of(
                Arguments.of("This text should contain two emails: mail@example.com and second@example.com! Voila!",
                        Arrays.asList("mail@example.com", "second@example.com")),
                Arguments.of("This text does not contain any email!", Collections.emptyList()),
                Arguments.of("info@domain.com is my email. Please contact to get more details",
                        Arrays.asList("info@domain.com"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForEmailRegex")
    void match(String input, List<String> expected) {
        List<String> result = EmailRegex.match(input);

        assertEquals(expected, result);
    }
}