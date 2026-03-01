package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.generator.Generator;
import dev.wproglk.rulez.interpreter.Interpreter;
import dev.wproglk.rulez.rules.ConcatenateRule;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;

import java.io.IOException;
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
        Ruleset rulesetFirstname = new Ruleset("firstname", firstName, backup);

        Rule lastName = new JsonPathRule("$.lastname");
        Ruleset rulesetLastname = new Ruleset("lastname", lastName, backup);

        Rule fullname = new ConcatenateRule("firstname", "lastname", " ");
        Ruleset rulesetFullname = new Ruleset("fullname", fullname);

        engine.setRulesets(rulesetFirstname, rulesetLastname, rulesetFullname);
        engine.generateDocumentation();
    }

    public void generateDocumentation() throws IOException {
        String html = Generator.generateHTML(rulesets);
        this.fileWriter.write(html);
    }

    public void setRulesets(Ruleset... rulesets) {
        this.rulesets = List.of(rulesets);
    }

    public void setRulesets(List<Ruleset> rulesets) {
        this.rulesets = rulesets;
    }

    public String executeRuleset(String source, String attribute) {
        return Interpreter.execute(rulesets, source)
                .getResult(attribute).getValue();
    }

    public String executeRuleset(String source) {
        return executeRuleset(source, "fullname");
    }
}
