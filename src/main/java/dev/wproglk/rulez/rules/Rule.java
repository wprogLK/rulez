package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;

import java.util.Map;

public interface Rule {
    /**
     *
     * @param source
     * @param results
     * @return OUTDATED the result; null if the rule could not be resolved (yet) OUTDATED
     */
    Result apply(String source, Map<String, Result> results);

    default String getTemplatename() {
        return getClass().getSimpleName();
    }
}
