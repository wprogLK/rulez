package dev.wproglk.rulez.rules;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record Ruleset(String targetName, List<? extends Rule> rules) {
    public Ruleset(String targetName, Rule... rules) {
        this(targetName, List.of(rules));
    }

    public String getTargetDescription() {
        return "Firstname of person";
    }

    public String apply(final String source, Map<String, String> results) {
        return rules.stream().map(rule -> rule.apply(source, results))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
