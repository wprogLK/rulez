package dev.wproglk.rulez.rules;

import java.util.Map;

public record ConcatenateRule(String targetNameA, String targetNameB, String delimiter) implements Rule {

    @Override
    public String apply(final String source, Map<String, String> results) {
        String valueA = results.get(targetNameA);
        String valueB = results.get(targetNameB);

        return valueA + delimiter + valueB;
    }
}
