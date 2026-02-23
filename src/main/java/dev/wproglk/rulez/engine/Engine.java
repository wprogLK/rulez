package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.generator.Generator;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Engine {
    private final FileWriterPort fileWriter;
    private List<Ruleset> rulesets;

    public Engine() {
        this(new FileWriterAdapter());
    }

    public Engine(final FileWriterPort fileWriter) {
        this.fileWriter = fileWriter;
    }

    static void main() throws IOException {
        final Engine engine = new Engine();
        Rule firstName = new JsonPathRule("$.firstname");
        Rule backup = new JsonPathRule("$.backup");

        Ruleset ruleset = new Ruleset("firstname", firstName, backup);

        engine.setRulesets(ruleset);
        engine.generateDocumentation();
    }

    public void generateDocumentation() throws IOException {
        String html = Generator.generateHTML(rulesets);
        this.fileWriter.write(html);
    }

    public void setRulesets(Ruleset... rulesets) {
        this.rulesets = List.of(rulesets);
    }

    public String executeRuleset(String source) {
        // TODO what if value does not yet exist? FutureCompletable?
        HashMap<String, String> results = new HashMap<>(); // targetName / attributeName, result

        for (Ruleset ruleset : this.rulesets) {
            results.put(ruleset.targetName(), ruleset.apply(source, results));
        }

        return results.get("fullname");
    }
}
