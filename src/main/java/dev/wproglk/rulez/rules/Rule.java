package dev.wproglk.rulez.rules;

import java.util.Map;

public interface Rule {
    String apply(String source, Map<String, String> results);

    default String getTemplatename() {
        return getClass().getSimpleName();
    }
}
