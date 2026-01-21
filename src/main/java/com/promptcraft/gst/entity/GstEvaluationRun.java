package com.promptcraft.gst.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "gst_evaluation_run")
public class GstEvaluationRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_version", nullable = false)
    private String ruleVersion;

    @Column(name = "matched_rules", nullable = false)
    private int matchedRules;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }
}
