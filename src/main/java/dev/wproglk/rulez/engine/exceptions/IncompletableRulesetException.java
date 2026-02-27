package dev.wproglk.rulez.engine.exceptions;

import dev.wproglk.rulez.rules.Ruleset;

import java.util.List;

public class IncompletableRulesetException extends RuntimeException {
    private final List<Ruleset> incompletableRulesets;

    public IncompletableRulesetException(List<Ruleset> incompletableRulesets) {
        this.incompletableRulesets = incompletableRulesets;
    }

    public List<Ruleset> getIncompletableRulesets() {
        return incompletableRulesets;
    }
}
