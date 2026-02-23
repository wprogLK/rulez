package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.rules.Ruleset;

public class Interpreter {

    public static String execute(final Ruleset ruleset, final String source) {
        return ruleset.apply(source, null);
    }
}
