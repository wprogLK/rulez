package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;
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

        Map<String, Result> intermediateResult = new HashMap<>();

        // act
        Result result = ruleset.apply(json, intermediateResult);

        // assert
//        assertThat(ruleset.isCompleted()).isTrue();
        assertThat(result.isCompleted()).isTrue();
        assertThat(result.getValue()).isEqualTo("Foo");
    }

    @Test
    void apply_noResult() {
        // arrange
        Ruleset ruleset = new Ruleset("test", new JsonPathRule("$.notExisting"));
        String json = "{}";


        Map<String, Result> intermediateResult = new HashMap<>();

        // act
        Result result = ruleset.apply(json, intermediateResult);

        // assert
        assertThat(ruleset.isCompleted()).isFalse();
        assertThat(result.isCompleted()).isFalse();
    }

}