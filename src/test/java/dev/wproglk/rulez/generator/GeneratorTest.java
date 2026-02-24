package dev.wproglk.rulez.generator;

import dev.wproglk.rulez.rules.ConcatenateRule;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GeneratorTest {
    @Test
    public void shouldGenerateSingleRuleRulesetHTML() {
        // arrange
        Rule firstName = new JsonPathRule("$.firstname");
        Rule backup = new JsonPathRule("$.backup");
        Ruleset rulesetFirstname = new Ruleset("firstname", firstName, backup);

        Rule lastName = new JsonPathRule("$.lastname");
        Ruleset rulesetLastname = new Ruleset("lastname", lastName, backup);

        Rule fullname = new ConcatenateRule("firstname", "lastname", " ");
        Ruleset rulesetFullname = new Ruleset("fullname", fullname);
        // act
        String result = Generator.generateHTML(List.of(rulesetFirstname, rulesetLastname, rulesetFullname));

        // assert
        Approvals.verifyHtml(result);
    }
}
