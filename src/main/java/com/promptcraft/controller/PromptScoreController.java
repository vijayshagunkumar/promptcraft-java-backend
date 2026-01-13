package com.promptcraft.controller;

import com.promptcraft.dto.ScoreBreakdown;
import com.promptcraft.service.PromptScoringService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prompts")
@Validated
public class PromptScoreController {

    @Autowired
    private PromptScoringService scoringService;

    @PostMapping("/score")
    public ResponseEntity<ScoreBreakdown> scorePrompt(
            @Valid @RequestBody ScoreRequest request) {

        ScoreBreakdown breakdown = scoringService.scorePromptWithBreakdown(
                request.getPrompt(),
                request.getTool()
        );

        return ResponseEntity.ok(breakdown);
    }

    @PostMapping("/score/simple")
    public ResponseEntity<ScoreResponse> scorePromptSimple(
            @Valid @RequestBody ScoreRequest request) {

        int score = scoringService.scorePrompt(
                request.getPrompt(),
                request.getTool()
        );

        return ResponseEntity.ok(new ScoreResponse(score));
    }

    // Request/Response DTOs (inner classes for simplicity)
    public static class ScoreRequest {
        @NotBlank(message = "Prompt cannot be empty")
        private String prompt;
        private String tool;

        public String getPrompt() { return prompt; }
        public void setPrompt(String prompt) { this.prompt = prompt; }

        public String getTool() { return tool; }
        public void setTool(String tool) { this.tool = tool; }
    }

    public static class ScoreResponse {
        private int score;

        public ScoreResponse() {}

        public ScoreResponse(int score) {
            this.score = score;
        }

        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
    }
}