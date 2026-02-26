package dev.wproglk.rulez.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RulesetTest {

    @Test
    void apply() {
        // arrange
        Ruleset ruleset = new Ruleset("test", new JsonPathRule("$.firstname"));
        String json = """
                {
                    "firstname": "Foo",
                }
                """;

        Map<String, String> intermediateResult = new HashMap<>();

        // act
        String result = ruleset.apply(json, intermediateResult);

        // assert
        assertThat(ruleset.isCompleted()).isTrue();
        assertThat(result).isEqualTo("Foo");
    }

    @Test
    void apply_noResult() {
        // arrange
        Ruleset ruleset = new Ruleset("test", new JsonPathRule("$.notExisting"));
        String json = "{}";


        Map<String, String> intermediateResult = new HashMap<>();

        // act
        String result = ruleset.apply(json, intermediateResult);

        // assert
        assertThat(ruleset.isCompleted()).isFalse();
    }

}