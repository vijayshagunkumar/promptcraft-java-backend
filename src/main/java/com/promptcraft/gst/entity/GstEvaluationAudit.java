package com.promptcraft.gst.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "gst_evaluation_audit")
public class GstEvaluationAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ruleVersion;

    @Column(nullable = false)
    private Integer matchedRules;

    @Column(nullable = false)
    private Instant evaluatedAt;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String requestFacts;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String evaluationResult;

    // ===== getters & setters =====

    public Long getId() {
        return id;
    }

    public String getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(String ruleVersion) {
        this.ruleVersion = ruleVersion;
    }

    public Integer getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(Integer matchedRules) {
        this.matchedRules = matchedRules;
    }

    public Instant getEvaluatedAt() {
        return evaluatedAt;
    }

    public void setEvaluatedAt(Instant evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }

    public String getRequestFacts() {
        return requestFacts;
    }

    public void setRequestFacts(String requestFacts) {
        this.requestFacts = requestFacts;
    }

    public String getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }
}
