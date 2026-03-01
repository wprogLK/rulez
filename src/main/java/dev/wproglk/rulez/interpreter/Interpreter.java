package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.rules.Ruleset;

public class Interpreter {

    public static Result execute(final Ruleset ruleset, final String source) {
        return ruleset.apply(source, null); // Why results = null?
    }
}
