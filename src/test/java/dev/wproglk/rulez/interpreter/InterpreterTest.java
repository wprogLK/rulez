package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.rules.Rule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InterpreterTest {

    @Test
    void extractFirstname(){
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
    void extractLastname(){
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
}
