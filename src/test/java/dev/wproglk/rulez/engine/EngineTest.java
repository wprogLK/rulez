package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.rules.ConcatenateRule;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EngineTest {

    @Mock
    FileWriterPort fileWriterPort;

    @Test
    public void shouldWriteDocumentation() throws IOException {
        // arrange
        JsonPathRule rule = new JsonPathRule("$.firstname");
        Engine engine = new Engine(fileWriterPort, rule);

        // act
        engine.generateDocumentation();

        // assert
        verify(fileWriterPort).write(anyString());
    }

    @Test
    void concatenateFullname() { // TODO order of ruleset should not matter
        // arrange
        final String source = """
                {
                    "firstname": "Foo",
                    "lastname": "Bar"
                }
                """;

        final Rule firstname = new JsonPathRule("$.firstname");
        final Rule lastname = new JsonPathRule("$.lastname");
        final Rule fullname = new ConcatenateRule("firstname", "lastname", " ");

        final Ruleset firstnameRuleset = new Ruleset("firstname", firstname);
        final Ruleset lastnameRuleset = new Ruleset("lastname", lastname);
        final Ruleset fullnameRuleset = new Ruleset("fullname", fullname);

        Engine engine = new Engine(fileWriterPort);
        engine.setRulesets(firstnameRuleset, lastnameRuleset, fullnameRuleset);

        // act
        String result = engine.executeRuleset(source);

        // assert
        assertThat(result).isEqualTo("Foo Bar");
    }
}
