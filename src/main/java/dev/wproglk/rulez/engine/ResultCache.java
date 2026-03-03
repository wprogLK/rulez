package dev.wproglk.rulez.engine;

import java.util.HashMap;
import java.util.function.Function;

public class ResultCache {
    private final HashMap<String, Result> results;

    public ResultCache() {
        this.results = new HashMap<>();
    }

    public Result getResult(String key) {
        return results.getOrDefault(key, Result.incompleteResult());
    }

    public void storeResult(String key, Result result) {
        results.put(key, result);
    }

    public static class Mappers {
        public static Function<ResultCache, String> singleAttributeMapper(String attribute) {
            return rc -> rc.getResult(attribute).getValue();
        }
    }
}
