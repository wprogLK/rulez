package dev.wproglk.rulez.rules;

import dev.wproglk.rulez.engine.Result;
import dev.wproglk.rulez.engine.ResultCache;

public record ConcatenateRule(String targetNameA, String targetNameB, String delimiter) implements Rule {

    @Override
    public Result apply(final String source, ResultCache results) {
        Result resultA = results.getResult(targetNameA);
        Result resultB = results.getResult(targetNameB);

        if (resultA.isIncompleted())
            return Result.incompleteResult();

        if (resultB.isIncompleted())
            return Result.incompleteResult();

        return Result.completedResult(resultA.getValue() + delimiter + resultB.getValue());
    }
}
