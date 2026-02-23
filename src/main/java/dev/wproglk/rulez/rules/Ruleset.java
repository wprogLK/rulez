package dev.wproglk.rulez.rules;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ruleset {
    private final List<? extends Rule> rules;
    private final String targetName;

    public Ruleset(String targetName, Rule... rules) {
        this.targetName = targetName;
        this.rules = List.of(rules);
    }

    public String apply(final String source, Map<String, String> results) {
        return rules.stream().map(rule -> rule.apply(source, results))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public String getTargetName() {
        return targetName;
    }
}
