package com.promptcraft.dto;

import java.util.ArrayList;
import java.util.List;

public class ScoreBreakdown {
    private int totalScore;
    private String grade;
    private int clarityAndIntent;
    private int contextAndRole;
    private int constraints;
    private int structure;
    private int completeness;
    private int toolCompatibility;
    private int noiseAndSafety;
    private List<String> strengths;
    private List<String> improvements;

    // Private constructor for builder
    private ScoreBreakdown() {
        this.strengths = new ArrayList<>();
        this.improvements = new ArrayList<>();
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ScoreBreakdown breakdown;

        private Builder() {
            breakdown = new ScoreBreakdown();
        }

        public Builder totalScore(int totalScore) {
            breakdown.totalScore = totalScore;
            return this;
        }

        public Builder grade(String grade) {
            breakdown.grade = grade;
            return this;
        }

        public Builder clarityAndIntent(int clarityAndIntent) {
            breakdown.clarityAndIntent = clarityAndIntent;
            return this;
        }

        public Builder contextAndRole(int contextAndRole) {
            breakdown.contextAndRole = contextAndRole;
            return this;
        }

        public Builder constraints(int constraints) {
            breakdown.constraints = constraints;
            return this;
        }

        public Builder structure(int structure) {
            breakdown.structure = structure;
            return this;
        }

        public Builder completeness(int completeness) {
            breakdown.completeness = completeness;
            return this;
        }

        public Builder toolCompatibility(int toolCompatibility) {
            breakdown.toolCompatibility = toolCompatibility;
            return this;
        }

        public Builder noiseAndSafety(int noiseAndSafety) {
            breakdown.noiseAndSafety = noiseAndSafety;
            return this;
        }

        public Builder strengths(List<String> strengths) {
            breakdown.strengths = strengths != null ? new ArrayList<>(strengths) : new ArrayList<>();
            return this;
        }

        public Builder improvements(List<String> improvements) {
            breakdown.improvements = improvements != null ? new ArrayList<>(improvements) : new ArrayList<>();
            return this;
        }

        public ScoreBreakdown build() {
            return breakdown;
        }
    }

    // Getters
    public int getTotalScore() { return totalScore; }
    public String getGrade() { return grade; }
    public int getClarityAndIntent() { return clarityAndIntent; }
    public int getContextAndRole() { return contextAndRole; }
    public int getConstraints() { return constraints; }
    public int getStructure() { return structure; }
    public int getCompleteness() { return completeness; }
    public int getToolCompatibility() { return toolCompatibility; }
    public int getNoiseAndSafety() { return noiseAndSafety; }
    public List<String> getStrengths() { return new ArrayList<>(strengths); }
    public List<String> getImprovements() { return new ArrayList<>(improvements); }

    // Setters
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setClarityAndIntent(int clarityAndIntent) { this.clarityAndIntent = clarityAndIntent; }
    public void setContextAndRole(int contextAndRole) { this.contextAndRole = contextAndRole; }
    public void setConstraints(int constraints) { this.constraints = constraints; }
    public void setStructure(int structure) { this.structure = structure; }
    public void setCompleteness(int completeness) { this.completeness = completeness; }
    public void setToolCompatibility(int toolCompatibility) { this.toolCompatibility = toolCompatibility; }
    public void setNoiseAndSafety(int noiseAndSafety) { this.noiseAndSafety = noiseAndSafety; }
    public void setStrengths(List<String> strengths) { this.strengths = new ArrayList<>(strengths); }
    public void setImprovements(List<String> improvements) { this.improvements = new ArrayList<>(improvements); }
}