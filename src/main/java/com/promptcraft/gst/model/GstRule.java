package com.promptcraft.gst.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GstRule {

    @JsonProperty("rule_id")
    private String ruleId;

    private String category;

    @JsonProperty("decision_type")
    private String decisionType;

    @JsonProperty("applies_when")
    private Map<String, Object> appliesWhen;

    private Map<String, Object> check;

    private Map<String, Object> parameters;

    @JsonProperty("risk_if_triggered")
    private Map<String, Object> riskIfTriggered;

    @JsonProperty("second_order_effects")
    private List<String> secondOrderEffects;

    @JsonProperty("foresight_tags")
    private List<Map<String, Object>> foresightTags;

    private Integer priority;

    private String template;

    // getters & setters

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(String decisionType) {
        this.decisionType = decisionType;
    }

    public Map<String, Object> getAppliesWhen() {
        return appliesWhen;
    }

    public void setAppliesWhen(Map<String, Object> appliesWhen) {
        this.appliesWhen = appliesWhen;
    }

    public Map<String, Object> getCheck() {
        return check;
    }

    public void setCheck(Map<String, Object> check) {
        this.check = check;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getRiskIfTriggered() {
        return riskIfTriggered;
    }

    public void setRiskIfTriggered(Map<String, Object> riskIfTriggered) {
        this.riskIfTriggered = riskIfTriggered;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
