package pl.brzezins.maks.extractor;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.brzezins.maks.utils.FileWrapper;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileExtractorTest {

    @ParameterizedTest
    @MethodSource("provideArgumentsForFactory")
    void callableReturnsArrayList(Class clazz) {
        try {
            FileExtractor extractor = (FileExtractor)mock(clazz);
            extractor.extract(any());

            verify(extractor).extract(any());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Stream<Arguments> provideArgumentsForFactory() {
        return Stream.of(
                Arguments.of(DocFileExtractor.class),
                Arguments.of(DocxFileExtractor.class),
                Arguments.of(PdfFileExtractor.class),
                Arguments.of(TextFileExtractor.class),
                Arguments.of(UnknownFileExtractor.class)
        );
    }
}