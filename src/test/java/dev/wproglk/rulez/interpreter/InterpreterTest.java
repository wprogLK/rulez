package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Ruleset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Named.named;

public class InterpreterTest {

    public static Stream<Arguments> precedenceBaseExtraction() {

        final String firstname = """
                {
                    "firstname": "Foo",
                    "lastname": "Bar"
                }
                """;

        final String lastname = """
                {
                    "lastname": "Bar"
                }
                """;

        final String empty = """
                {
                
                }
                """;

        final String otherAttribute = """
                {
                  "fullname": "Foo Bar"
                }
                """;

        return Stream.of(
                Arguments.of(named("entire json", firstname), "Foo"),
                Arguments.of(named("single attribute json", lastname), "Bar"),
                Arguments.of(named("irrelevant attribute json", otherAttribute), null),
                Arguments.of(named("empty json", empty), null)
        );
    }

    @Test
    void extractFirstname() {
        // arrange
        final String source = """
                {
                    "firstname": "Foo"
                }
                """;
        final JsonPathRule rule = new JsonPathRule("$.firstname");
        final Ruleset ruleset = new Ruleset("firstname", rule);

        // act
        Result result = Interpreter.execute(ruleset, source);

        // assert
        assertThat(result.getValue()).isEqualTo("Foo");
    }

    @Test
    void extractLastname() {
        // arrange
        final String source = """
                {
                    "lastname": "Bar"
                }
                """;
        final JsonPathRule rule = new JsonPathRule("$.lastname");
        final Ruleset ruleset = new Ruleset("lastname", rule);

        // act
        Result result = Interpreter.execute(ruleset, source);
        // assert
        assertThat(result.getValue()).isEqualTo("Bar");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void precedenceBaseExtraction(String source, String expectedResult) {
        // arrange
        final JsonPathRule firstname = new JsonPathRule("$.firstname");
        final JsonPathRule lastname = new JsonPathRule("$.lastname");

        final Ruleset nameRuleset = new Ruleset("name", firstname, lastname);

        // act
        Result result = Interpreter.execute(nameRuleset, source);

        // assert
        assertThat(result.getValue()).isEqualTo(expectedResult);
    }
}
