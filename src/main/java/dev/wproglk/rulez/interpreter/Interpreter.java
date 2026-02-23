package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.rules.Rule;
import dev.wproglk.rulez.rules.Ruleset;

public class Interpreter {

    @Deprecated
    public static String execute(final Rule rule, final String source) {
        return rule.apply(source, null);
    }

    public static String execute(final Ruleset ruleset, final String source) {
        return ruleset.apply(source, null);
    }
}
