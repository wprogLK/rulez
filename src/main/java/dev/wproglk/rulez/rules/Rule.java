package dev.wproglk.rulez.rules;

import java.util.Map;

public interface Rule {
    String apply(String json, Map<String, String> results);
}
