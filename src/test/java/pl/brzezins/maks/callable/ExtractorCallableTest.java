package pl.brzezins.maks.callable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.brzezins.maks.extractor.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExtractorCallableTest {
    @Mock
    FileExtractor fileExtractor;

    @InjectMocks
    ExtractorCallable callable;

    @Test
    void verifiedExtractMethod() {
        when(fileExtractor.extract(any())).thenReturn(anyList());

        callable.call();

        verify(fileExtractor).extract(any());
    }
}