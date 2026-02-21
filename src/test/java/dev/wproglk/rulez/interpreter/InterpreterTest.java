package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
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
        final String json = """
                {
                    "firstname": "Foo"
                }
                """;
        final Rule rule = new Rule("$.firstname");

        // act
        String result = Interpreter.execute(rule, json);

        // assert
        assertThat(result).isEqualTo("Foo");
    }

    @Test
    void extractLastname() {
        // arrange
        final String json = """
                {
                    "lastname": "Bar"
                }
                """;
        final Rule rule = new Rule("$.lastname");

        // act
        String result = Interpreter.execute(rule, json);

        // assert
        assertThat(result).isEqualTo("Bar");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource
    void precedenceBaseExtraction(String json, String expectedResult) {
        // arrange
        final Rule firstname = new Rule("$.firstname");
        final Rule lastname = new Rule("$.lastname");

        final List<Rule> rules = List.of(firstname, lastname);

        final Ruleset nameRuleset = new Ruleset(rules);

        // act
        String result = Interpreter.execute(nameRuleset, json);

        // assert
        assertThat(result).isEqualTo(expectedResult);
    }
}
