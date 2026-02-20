package dev.wproglk.rulez.generator;

import dev.wproglk.rulez.rules.Rule;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class GeneratorTest {
    @Test
    public void shouldGenerateFirstnameMappingHTML() {
        // arrange
        final Rule rule = new Rule("$.firstname");

        // act
        String result = Generator.generateHTML(rule);

        // assert
       Approvals.verifyHtml(result);
    }
}
