package org.automation.utils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final Map<String, String> context = new HashMap<>();

    private TestContext() {}

    public static void set(String key, String value) {
        context.put(key, value);
    }

    public static String get(String key) {
        return context.getOrDefault(key, "");
    }

    public static void clear() {
        context.clear();
    }
}