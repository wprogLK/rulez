package dev.wproglk.rulez.rules;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.util.Map;

public record JsonPathRule(String jsonPath) implements Rule {

    @Override
    public String apply(final String source, Map<String, String> result) {
        try {
            return JsonPath.read(source, jsonPath);
        }
        catch (PathNotFoundException e) {
            return null;
        }
    }
}
