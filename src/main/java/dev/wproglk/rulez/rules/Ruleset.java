package dev.wproglk.rulez.rules;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class Ruleset {
    private final String targetName;
    private final List<? extends Rule> rules;
    private boolean completed;

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

    public String apply(final String source, Map<String, String> results) {
        Optional<String> result = rules.stream().map(rule -> rule.apply(source, results))
                .filter(Objects::nonNull)
                .findFirst();

        this.completed = result.isPresent();

        return result.orElse(null);
    }

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
