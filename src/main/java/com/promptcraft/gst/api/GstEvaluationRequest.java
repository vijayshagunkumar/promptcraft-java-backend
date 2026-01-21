package com.promptcraft.gst.api;

import java.util.Map;

public class GstEvaluationRequest {

    private String version;
    private Map<String, Object> facts;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, Object> getFacts() {
        return facts;
    }

    public void setFacts(Map<String, Object> facts) {
        this.facts = facts;
    }
}
