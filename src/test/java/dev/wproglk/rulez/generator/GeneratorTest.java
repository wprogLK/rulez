package dev.wproglk.rulez.generator;

import dev.wproglk.rulez.rules.JsonPathRule;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
    @Test
    public void shouldGenerateFirstnameMappingHTML() {
        // arrange
        final JsonPathRule rule = new JsonPathRule("$.firstname");

        // act
        String result = Generator.generateHTML(rule);

        // assert
       Approvals.verifyHtml(result);
    }
}
