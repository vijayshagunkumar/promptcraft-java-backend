package com.promptcraft.dto;

public class PromptScoreRequest {
    private String prompt;
    private Metadata metadata;

    // Constructors
    public PromptScoreRequest() {}

    public PromptScoreRequest(String prompt, Metadata metadata) {
        this.prompt = prompt;
        this.metadata = metadata;
    }

    // Getters and Setters
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    // Inner class for metadata
    public static class Metadata {
        private String source;
        private String timestamp;

        // Getters and Setters
        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}