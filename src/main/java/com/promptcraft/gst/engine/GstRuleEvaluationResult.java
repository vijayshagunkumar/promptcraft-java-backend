package com.promptcraft.gst.engine;

import java.util.List;
import java.util.Map;

public class GstRuleEvaluationResult {

    private String ruleId;
    private String decisionType;
    private Integer priority;

    private String riskLevel;
    private String riskReason;

    private Map<String, Object> lawReference;
    private List<String> secondOrderEffects;
    private List<Map<String, Object>> foresightTags;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(String decisionType) {
        this.decisionType = decisionType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskReason() {
        return riskReason;
    }

    public void setRiskReason(String riskReason) {
        this.riskReason = riskReason;
    }

    public Map<String, Object> getLawReference() {
        return lawReference;
    }

    public void setLawReference(Map<String, Object> lawReference) {
        this.lawReference = lawReference;
    }

    public List<String> getSecondOrderEffects() {
        return secondOrderEffects;
    }

    public void setSecondOrderEffects(List<String> secondOrderEffects) {
        this.secondOrderEffects = secondOrderEffects;
    }

    public List<Map<String, Object>> getForesightTags() {
        return foresightTags;
    }

    public void setForesightTags(List<Map<String, Object>> foresightTags) {
        this.foresightTags = foresightTags;
    }
}
