package dev.wproglk.rulez.rules;

import java.util.Map;

public class ConcatenateRule implements Rule {

    private final String attributeNameA;
    private final String attributeNameB;
    private final String delimiter;

    public ConcatenateRule(final String attributeNameA, final String attributeNameB, final String delimiter) {
        this.attributeNameA = attributeNameA;
        this.attributeNameB = attributeNameB;
        this.delimiter = delimiter;
    }

    @Override
    public String apply(final String json, Map<String, String> results) {
        String valueA = results.get(attributeNameA);
        String valueB = results.get(attributeNameB);

        return valueA + delimiter + valueB;
    }

}
