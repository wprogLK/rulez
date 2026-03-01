package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.engine.ResultCache;
import org.junit.jupiter.api.Test;

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

        // act
        Result result = ruleset.apply(json, new ResultCache());

        // assert
        assertThat(result.isCompleted()).isTrue();
        assertThat(result.getValue()).isEqualTo("Foo");
    }

    @Test
    void apply_noResult() {
        // arrange
        Ruleset ruleset = new Ruleset("test", new JsonPathRule("$.notExisting"));
        String json = "{}";


        // act
        Result result = ruleset.apply(json, new ResultCache());

        // assert
        assertThat(result.isCompleted()).isFalse();
    }
}