package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.engine.ResultCache;

import java.util.List;

public record Ruleset(String targetName, List<? extends Rule> rules) {

    public Ruleset(String targetName, Rule... rules) {
        this(targetName, List.of(rules));
    }

    public String getTargetDescription() {
        return "Firstname of person";
    }

    public Result apply(final String source, ResultCache results) {
        return rules.stream().map(rule -> rule.apply(source, results))
                .filter(Result::isCompleted)
                .findFirst()
                .orElse(Result.incompleteResult());
    }
}
