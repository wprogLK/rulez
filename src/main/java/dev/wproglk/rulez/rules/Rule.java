package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.engine.ResultCache;

public interface Rule {
    Result apply(String source, ResultCache results);

    default String getTemplatename() {
        return getClass().getSimpleName();
    }
}
