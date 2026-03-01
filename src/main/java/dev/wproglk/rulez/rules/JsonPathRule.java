package dev.wproglk.rulez.rules;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.engine.ResultCache;

public record JsonPathRule(String jsonPath) implements Rule {

    @Override
    public Result apply(final String source, ResultCache result) {
        try {
            String value = JsonPath.read(source, jsonPath);
            return Result.completedResult(value);
        } catch (PathNotFoundException e) {
            return Result.incompleteResult();
        }
    }
}
