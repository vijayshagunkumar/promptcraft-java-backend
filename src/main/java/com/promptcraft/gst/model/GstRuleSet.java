package com.promptcraft.gst.model;

import java.util.List;
import java.util.Map;

public class GstRuleSet {

    private Map<String, Object> metadata;
    private String schema_version;
    private List<String> rule_templates_used;
    private List<GstRule> rules;
    private Map<String, Object> schema_definition;

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getSchema_version() {
        return schema_version;
    }

    public void setSchema_version(String schema_version) {
        this.schema_version = schema_version;
    }

    public List<String> getRule_templates_used() {
        return rule_templates_used;
    }

    public void setRule_templates_used(List<String> rule_templates_used) {
        this.rule_templates_used = rule_templates_used;
    }

    public List<GstRule> getRules() {
        return rules;
    }

    public void setRules(List<GstRule> rules) {
        this.rules = rules;
    }

    public Map<String, Object> getSchema_definition() {
        return schema_definition;
    }

    public void setSchema_definition(Map<String, Object> schema_definition) {
        this.schema_definition = schema_definition;
    }
}
