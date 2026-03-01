package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;

import java.util.Map;

public record ConcatenateRule(String targetNameA, String targetNameB, String delimiter) implements Rule {

    @Override
    public Result apply(final String source, Map<String, Result> results) {
        String valueA = results.get(targetNameA).getValue();
        String valueB = results.get(targetNameB).getValue();

        Result resultA = results.get(targetNameA);
        Result resultB = results.get(targetNameB);

        if (resultA.isIncompleted())
            return Result.incompleteResult();

        if (resultB.isIncompleted())
            return Result.incompleteResult();

        return Result.completedResult(valueA + delimiter + valueB);
    }
}
