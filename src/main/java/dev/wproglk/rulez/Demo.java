package dev.wproglk.rulez;

import dev.wproglk.rulez.engine.Engine;
import dev.wproglk.rulez.engine.ResultCache;
import dev.wproglk.rulez.rules.ConcatenateRule;
import dev.wproglk.rulez.rules.JsonPathRule;
import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;

import java.io.IOException;

public class Demo {
    static void main() throws IOException {
        final Engine<String> engine = new Engine<>(ResultCache.Mappers.singleAttributeMapper("fullname"));

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
}
