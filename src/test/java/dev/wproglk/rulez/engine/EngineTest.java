package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.rules.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EngineTest {

    @Mock
    FileWriterPort fileWriterPort;

    @Test
    public void shouldWriteDocumentation() throws IOException {
        // arrange
        Rule rule = new Rule("$.firstname");
        Engine engine = new Engine(fileWriterPort, rule);

        // act
        engine.generateDocumentation();

        // assert
        verify(fileWriterPort).write(anyString());
    }
}
