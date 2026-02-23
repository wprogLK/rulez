package dev.wproglk.rulez.rules;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.Map;

public class JsonPathRule implements Rule {

    private final String jsonPath;

    public JsonPathRule(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public String apply(final String source, Map<String, String> result) {
        try {
            return JsonPath.read(source, jsonPath);
        }
        catch (PathNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public String getAttributeName() {
        return "firstname";
    }
}
