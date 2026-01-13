package com.promptcraft.dto;

public class PromptScoreResponse {

    private int score;
    private String message;

    public PromptScoreResponse(int score, String message) {
        this.score = score;
        this.message = message;
    }

    public int getScore() {
        return score;
    }

    public String getMessage() {
        return message;
    }
}
