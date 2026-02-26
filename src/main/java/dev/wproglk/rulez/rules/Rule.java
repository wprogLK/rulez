package dev.wproglk.rulez.rules;

import java.util.Map;

public interface Rule {
    /**
     *
     * @param source
     * @param results
     * @return the result; null if the rule could not be resolved (yet)
     */
    String apply(String source, Map<String, String> results);

    default String getTemplatename() {
        return getClass().getSimpleName();
    }
}
