package com.promptcraft.gst.api;

import com.promptcraft.gst.engine.GstRuleEvaluationResult;

import java.util.List;

public class GstEvaluationResponse {

    private String ruleVersion;
    private int matchedRules;
    private List<GstRuleEvaluationResult> results;

    public String getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(String ruleVersion) {
        this.ruleVersion = ruleVersion;
    }

    public int getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(int matchedRules) {
        this.matchedRules = matchedRules;
    }

    public List<GstRuleEvaluationResult> getResults() {
        return results;
    }

    public void setResults(List<GstRuleEvaluationResult> results) {
        this.results = results;
    }
}
