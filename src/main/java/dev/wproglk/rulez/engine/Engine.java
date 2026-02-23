package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.generator.Generator;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Ruleset;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Engine {
    private final FileWriterPort fileWriter;
    @Deprecated
    private final JsonPathRule rule;
    private List<Ruleset> rulesets;

    public Engine(final FileWriterPort fileWriterPort, final JsonPathRule rule) {
        this.fileWriter = fileWriterPort;
        this.rule = rule;
    }

    public Engine(final FileWriterPort fileWriter) {
        this.fileWriter = fileWriter;
        this.rule = null;
    }

    public Engine(final JsonPathRule rule) {
        this(new FileWriterAdapter(), rule);
    }

    static void main() throws IOException {
        final Engine engine = new Engine(new JsonPathRule("$.firstname"));
        engine.generateDocumentation();
    }

    public void generateDocumentation() throws IOException {
        String html = Generator.generateHTML(rule);
        this.fileWriter.write(html);
    }

    public void setRulesets(Ruleset... rulesets) {
        this.rulesets = List.of(rulesets);
    }

    public String executeRuleset(String source) {
        // TODO what if value does not yet exist? FutureCompletable?
        HashMap<String, String> results = new HashMap<>(); // targetName / attributeName, result

        for (Ruleset ruleset : this.rulesets) {
            results.put(ruleset.getTargetName(), ruleset.apply(source, results));
        }

        return results.get("fullname");
    }
}
