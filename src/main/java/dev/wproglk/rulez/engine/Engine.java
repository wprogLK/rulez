package dev.wproglk.rulez.engine;

import dev.wproglk.rulez.engine.exceptions.IncompletableRulesetException;
import dev.wproglk.rulez.generator.Generator;
import dev.wproglk.rulez.rules.ConcatenateRule;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

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

    public String executeRuleset(String source) {
        HashMap<String, Result> results = new HashMap<>(); // targetName / attributeName, result
        List<Ruleset> pendingRulesets = new ArrayList<>(this.rulesets);

        boolean noAdditionalRulesetHasBeenResolved = true;

        while (!pendingRulesets.isEmpty()) {
            ListIterator<Ruleset> iterator = pendingRulesets.listIterator();

            while (iterator.hasNext()) {
                Ruleset ruleset = iterator.next();

                Result result = ruleset.apply(source, results);
                if (result.isCompleted()) {
                    results.put(ruleset.targetName(), result);
                    noAdditionalRulesetHasBeenResolved = false;
                    iterator.remove();
                }
            }

            if (noAdditionalRulesetHasBeenResolved) {
                throw new IncompletableRulesetException(pendingRulesets);
            }
        }

        return results.get("fullname").getValue();
    }

    private Stream<Ruleset> incompletedRulesets() {
        return rulesets.stream().filter(not(Ruleset::isCompleted));
    }
}
