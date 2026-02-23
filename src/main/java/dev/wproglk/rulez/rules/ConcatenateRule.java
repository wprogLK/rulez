package dev.wproglk.rulez.rules;

import java.util.Map;

public class ConcatenateRule implements Rule {

    private final String targetNameA;
    private final String targetNameB;
    private final String delimiter;

    public ConcatenateRule(final String targetNameA, final String targetNameB, final String delimiter) {
        this.targetNameA = targetNameA;
        this.targetNameB = targetNameB;
        this.delimiter = delimiter;
    }

    @Override
    public String apply(final String source, Map<String, String> results) {
        String valueA = results.get(targetNameA);
        String valueB = results.get(targetNameB);

        return valueA + delimiter + valueB;
    }

}
