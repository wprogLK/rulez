package dev.wproglk.rulez.rules;

import com.jayway.jsonpath.JsonPath;

public class Rule {

    private final String jsonPath;

    public Rule(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String apply(final String json) {
        return JsonPath.read(json, jsonPath);
    }
}
