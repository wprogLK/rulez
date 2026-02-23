package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;

public class Interpreter {

    @Deprecated
    public static String execute(final Rule rule, final String json) {
        return rule.apply(json, null);
    }

    public static String execute(final Ruleset ruleset, final String json) {
        return ruleset.apply(json, null);
    }
}
