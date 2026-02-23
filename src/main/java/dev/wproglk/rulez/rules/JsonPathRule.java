package dev.wproglk.rulez.rules;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

public class JsonPathRule implements Rule {

    private final String jsonPath;

    public JsonPathRule(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
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
