package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;

import java.util.List;
import java.util.Map;

public final class Ruleset {
    private final String targetName;
    private final List<? extends Rule> rules;
    @Deprecated
    private final boolean completed;

    public Ruleset(String targetName, List<? extends Rule> rules) {
        this.targetName = targetName;
        this.rules = rules;
        this.completed = false;
    }

    public Ruleset(String targetName, Rule... rules) {
        this(targetName, List.of(rules));
    }

    public String getTargetDescription() {
        return "Firstname of person";
    }

    public Result apply(final String source, Map<String, Result> results) {
        return rules.stream().map(rule -> rule.apply(source, results))
                .filter(Result::isCompleted)
                .findFirst()
                .orElse(Result.incompleteResult()); // should not be needed 
//
//        this.completed = result.isPresent();
//
//        return result.orElse(null);
    }

    @Deprecated
    public boolean isCompleted() {
        return completed;
    }

    public String targetName() {
        return targetName;
    }

    public List<? extends Rule> rules() {
        return rules;
    }
}
