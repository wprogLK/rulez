package dev.wproglk.rulez.rules;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class Rule {

    private final String jsonPath;

    public Rule(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String apply(final String json) {
        try {
            return JsonPath.read(json, jsonPath);
        }
        catch (PathNotFoundException e) {
            return null;
        }
    }

    public String getAttributeName() {
        return "firstname";
    }
}
