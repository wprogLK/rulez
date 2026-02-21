package dev.wproglk.rulez.rules;

import java.util.List;
import java.util.Objects;

public class Ruleset {
    private final List<Rule> rules;

    public Ruleset(final List<Rule> rules) {
        this.rules = rules;
    }

    public String apply(final String json) {
        return rules.stream().map(rule -> rule.apply(json))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
