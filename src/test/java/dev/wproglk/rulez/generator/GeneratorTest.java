package dev.wproglk.rulez.generator;

import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Ruleset;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GeneratorTest {
    @Test
    public void shouldGenerateSingleRuleRulesetHTML() {
        // arrange
        final JsonPathRule rule = new JsonPathRule("$.firstname");
        final Ruleset ruleset = new Ruleset("firstname", rule);

        // act
        String result = Generator.generateHTML(List.of(ruleset));

        // assert
        Approvals.verifyHtml(result);
    }
}
