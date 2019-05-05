package pl.brzezins.maks.factory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.brzezins.maks.extractor.*;
import pl.brzezins.maks.utils.FileWrapper;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class FileExtractorFactoryTest {
    @Mock
    FileWrapper fileWrapper;

    @InjectMocks
    FileExtractorFactory factory;

    private static Stream<Arguments> provideArgumentsForFactory() {
        return Stream.of(
                Arguments.of("doc", false, DocFileExtractor.class),
                Arguments.of("docx", false, DocxFileExtractor.class),
                Arguments.of("pdf", false, PdfFileExtractor.class),
                Arguments.of("txt", true, TextFileExtractor.class),
                Arguments.of("txt", false, UnknownFileExtractor.class),
                Arguments.of("img", false, UnknownFileExtractor.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForFactory")
    void factoryProducesCorrectFileExtractor(String extension, boolean textFile, Class clazz) {
        lenient().when(fileWrapper.getExtension()).thenReturn(extension);
        lenient().when(fileWrapper.isTextFile()).thenReturn(textFile);

        FileExtractor extractor = factory.create();

        assertEquals(extractor.getClass(), clazz);
    }
}