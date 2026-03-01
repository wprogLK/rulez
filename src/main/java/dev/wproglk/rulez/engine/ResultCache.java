package dev.wproglk.rulez.engine;

import java.util.HashMap;

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
}
