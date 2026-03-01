package dev.wproglk.rulez.interpreter;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.engine.ResultCache;
import dev.wproglk.rulez.engine.exceptions.IncompletableRulesetException;
import dev.wproglk.rulez.rules.Ruleset;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Interpreter {

    @Deprecated
    public static Result execute(final Ruleset ruleset, final String source) {
        return ruleset.apply(source, null); // Why results = null?
    }

    public static ResultCache execute(final List<Ruleset> rulesets, final String source) {
        ResultCache results = new ResultCache();
        List<Ruleset> pendingRulesets = new ArrayList<>(rulesets);

        boolean noAdditionalRulesetHasBeenResolved = true;

        while (!pendingRulesets.isEmpty()) {
            ListIterator<Ruleset> iterator = pendingRulesets.listIterator();

            while (iterator.hasNext()) {
                Ruleset ruleset = iterator.next();

                Result result = ruleset.apply(source, results);
                if (result.isCompleted()) {
                    results.storeResult(ruleset.targetName(), result);
                    noAdditionalRulesetHasBeenResolved = false;
                    iterator.remove();
                }
            }

            if (noAdditionalRulesetHasBeenResolved) {
                throw new IncompletableRulesetException(pendingRulesets);
            }
        }

        return results;
    }
}
