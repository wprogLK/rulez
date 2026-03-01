package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.engine.exceptions.IncompletableRulesetException;
import dev.wproglk.rulez.rules.ConcatenateRule;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Named.named;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EngineTest {

    @Mock
    FileWriterPort fileWriterPort;

    public static Stream<Arguments> concatenateFullname() {
        final Rule firstname = new JsonPathRule("$.firstname");
        final Rule lastname = new JsonPathRule("$.lastname");
        final Rule fullname = new ConcatenateRule("firstname", "lastname", " ");

        final Ruleset firstnameRuleset = new Ruleset("firstname", firstname);
        final Ruleset lastnameRuleset = new Ruleset("lastname", lastname);
        final Ruleset fullnameRuleset = new Ruleset("fullname", fullname);

        return Stream.of(
                Arguments.of(named("logical order: first, last ,full", List.of(firstnameRuleset, lastnameRuleset, fullnameRuleset))),
                Arguments.of(named("reversed logical order: full, last ,first", List.of(fullnameRuleset, lastnameRuleset, firstnameRuleset)))
        );
    }

    @Test
    public void shouldWriteDocumentation() throws IOException {
        // arrange
        JsonPathRule rule = new JsonPathRule("$.firstname");
        Ruleset ruleset = new Ruleset("firstname", rule);

        Engine engine = new Engine(fileWriterPort);
        engine.setRulesets(ruleset);

        // act
        engine.generateDocumentation();

        // assert
        verify(fileWriterPort).write(anyString());
    }

    @ParameterizedTest
    @MethodSource
    void concatenateFullname(List<Ruleset> rulesets) {
        // arrange
        final String source = """
                {
                    "firstname": "Foo",
                    "lastname": "Bar"
                }
                """;

        Engine engine = new Engine(fileWriterPort);
        engine.setRulesets(rulesets);

        // act
        String result = engine.executeRuleset(source);

        // assert
        assertThat(result).isEqualTo("Foo Bar");
    }

    @Test
    void incompletableRulesets_missingSource() {
        // arrange
        final String source = "{}";

        Rule rule = new JsonPathRule("$.firstname");
        Ruleset ruleset = new Ruleset("firstname", rule);

        Engine engine = new Engine(fileWriterPort);
        engine.setRulesets(ruleset);

        // act
        IncompletableRulesetException exception = catchThrowableOfType(IncompletableRulesetException.class, () -> engine.executeRuleset(source));

        // assert
        assertThat(exception).isInstanceOf(IncompletableRulesetException.class)
                .satisfies(e -> assertThat(exception.getIncompletableRulesets()).isNotEmpty());
    }

    @Disabled
    @Test
    void incompletableRulesets_circularRulesets() {
        // arrange

        // act

        // assert
    }
}
