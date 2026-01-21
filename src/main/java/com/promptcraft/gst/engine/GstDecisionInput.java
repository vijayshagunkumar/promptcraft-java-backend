package com.promptcraft.gst.engine;

import java.util.HashMap;
import java.util.Map;

public class GstDecisionInput {

    private final Map<String, Object> facts = new HashMap<>();

    public GstDecisionInput() {
    }

    public GstDecisionInput(Map<String, Object> initialFacts) {
        if (initialFacts != null) {
            this.facts.putAll(initialFacts);
        }
    }

    public void put(String key, Object value) {
        facts.put(key, value);
    }

    public Object get(String key) {
        return facts.get(key);
    }

    public Map<String, Object> getFacts() {
        return facts;
    }
}
