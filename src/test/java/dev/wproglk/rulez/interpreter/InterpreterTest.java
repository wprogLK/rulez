package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTest {

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

    @Test
    void precedenceBaseExtraction() {
        // arrange
        final String json = """
                {
                    "lastname": "Bar"
                }
                """;

        final Rule firstname = new Rule("$.firstname");
        final Rule lastname = new Rule("$.lastname");

        final List<Rule> rules = List.of(firstname, lastname);

        final Ruleset nameRuleset = new Ruleset(rules);

        // act
        String result = Interpreter.execute(nameRuleset, json);

        // assert
        assertThat(result).isEqualTo("Bar");
    }
}
