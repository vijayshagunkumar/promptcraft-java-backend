package com.promptcraft.service;

import com.promptcraft.dto.ScoreBreakdown;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromptScoringService {

    // =============================
    // PUBLIC API
    // =============================
    public ScoreBreakdown scorePromptWithBreakdown(String prompt, String tool) {
        // Guard clause for null/empty prompts
        if (prompt == null || prompt.isBlank()) {
            return ScoreBreakdown.builder()
                    .totalScore(0)
                    .grade("Poor")
                    .clarityAndIntent(0)
                    .contextAndRole(0)
                    .constraints(0)
                    .structure(0)
                    .completeness(0)
                    .toolCompatibility(0)
                    .noiseAndSafety(0)
                    .strengths(List.of())
                    .improvements(List.of("Provide a non-empty prompt"))
                    .build();
        }

        // Initialize collections for feedback
        List<String> strengths = new ArrayList<>();
        List<String> improvements = new ArrayList<>();

        // Calculate individual scores with feedback
        int clarityScore = scoreClarityAndIntent(prompt, strengths, improvements);
        int contextScore = scoreContextAndRole(prompt, strengths, improvements);
        int constraintsScore = scoreConstraints(prompt, strengths, improvements);
        int structureScore = scoreStructure(prompt, strengths, improvements);
        int completenessScore = scoreCompleteness(prompt, strengths, improvements);
        int toolScore = scoreToolCompatibility(prompt, tool, strengths, improvements);
        int safetyScore = scoreNoiseAndSafety(prompt, strengths, improvements);

        // Calculate total
        int totalScore = clarityScore + contextScore + constraintsScore +
                structureScore + completenessScore + toolScore + safetyScore;
        totalScore = Math.min(totalScore, 100);

        // Build and return breakdown
        return ScoreBreakdown.builder()
                .totalScore(totalScore)
                .grade(determineGrade(totalScore))
                .clarityAndIntent(clarityScore)
                .contextAndRole(contextScore)
                .constraints(constraintsScore)
                .structure(structureScore)
                .completeness(completenessScore)
                .toolCompatibility(toolScore)
                .noiseAndSafety(safetyScore)
                .strengths(strengths)
                .improvements(improvements)
                .build();
    }

    // Backward compatibility
    public int scorePrompt(String prompt, String tool) {
        if (prompt == null || prompt.isBlank()) {
            return 0;
        }

        int score = 0;
        List<String> dummyStrengths = new ArrayList<>();
        List<String> dummyImprovements = new ArrayList<>();

        score += scoreClarityAndIntent(prompt, dummyStrengths, dummyImprovements);
        score += scoreContextAndRole(prompt, dummyStrengths, dummyImprovements);
        score += scoreConstraints(prompt, dummyStrengths, dummyImprovements);
        score += scoreStructure(prompt, dummyStrengths, dummyImprovements);
        score += scoreCompleteness(prompt, dummyStrengths, dummyImprovements);
        score += scoreToolCompatibility(prompt, tool, dummyStrengths, dummyImprovements);
        score += scoreNoiseAndSafety(prompt, dummyStrengths, dummyImprovements);

        return Math.min(score, 100);
    }

    // =============================
    // SCORING DIMENSIONS (FIXED VERSIONS)
    // =============================

    private int scoreClarityAndIntent(String prompt, List<String> strengths, List<String> improvements) {
        int score = 0;
        boolean hasActionVerb = false;
        boolean hasGoodLength = false;

        // FIXED: Check for action verbs using contains() not matches()
        String lowerPrompt = prompt.toLowerCase();
        if (lowerPrompt.contains("generate") || lowerPrompt.contains("write") ||
                lowerPrompt.contains("create") || lowerPrompt.contains("analyze") ||
                lowerPrompt.contains("summarize") || lowerPrompt.contains("design") ||
                lowerPrompt.contains("explain") || lowerPrompt.contains("develop") ||
                lowerPrompt.contains("produce") || lowerPrompt.contains("compose") ||
                lowerPrompt.contains("draft") || lowerPrompt.contains("formulate") ||
                lowerPrompt.contains("construct") || lowerPrompt.contains("build") ||
                lowerPrompt.contains("make") || lowerPrompt.contains("prepare")) {
            score += 10;
            hasActionVerb = true;
            strengths.add("Clear action verb present");
        } else {
            improvements.add("Add an action verb (generate, write, create, analyze, summarize)");
        }

        if (prompt.length() > 30) {
            score += 10;
            hasGoodLength = true;
            strengths.add("Adequate prompt length for clear intent");
        } else {
            improvements.add("Expand prompt (minimum 30 characters recommended)");
        }

        if (hasActionVerb && hasGoodLength) {
            strengths.add("Clear and actionable intent");
        }

        return score;
    }

    private int scoreContextAndRole(String prompt, List<String> strengths, List<String> improvements) {
        // FIXED: Use contains() for better role detection
        String lowerPrompt = prompt.toLowerCase();

        if (lowerPrompt.contains("act as") ||
                lowerPrompt.contains("you are") ||
                lowerPrompt.contains("as a ") ||
                lowerPrompt.contains("assume the role") ||
                lowerPrompt.contains("in the role of") ||
                lowerPrompt.contains("pretend to be") ||
                lowerPrompt.contains("simulate being") ||
                lowerPrompt.contains("role:") ||
                lowerPrompt.contains("role of") ||
                lowerPrompt.matches(".*as an? [a-zA-Z]+.*")) {  // Matches "as a writer", "as an expert"
            strengths.add("Role/context clearly defined");
            return 15;
        }
        improvements.add("Define role (e.g., 'Act as a...', 'You are a...')");
        return 0;
    }

    private int scoreConstraints(String prompt, List<String> strengths, List<String> improvements) {
        // FIXED: Comprehensive constraint detection
        String lowerPrompt = prompt.toLowerCase();

        String[] constraintKeywords = {
                "limit", "constraint", "tone", "style", "format", "must", "required",
                "avoid", "include", "use", "should", "cannot", "do not", "restrict",
                "parameter", "boundary", "guideline", "specification", "requirement",
                "rule", "follow", "adhere", "comply", "exclude", "omit", "skip",
                "max", "min", "minimum", "maximum", "at least", "at most", "only",
                "exactly", "precisely", "strictly", "never", "always", "ensure",
                "maintain", "keep", "preserve", "uphold", "stick to", "abide by"
        };

        for (String keyword : constraintKeywords) {
            if (lowerPrompt.contains(keyword)) {
                strengths.add("Includes specific constraints/requirements");
                return 15;
            }
        }

        improvements.add("Add constraints (tone, style, format, length, avoid, requirements)");
        return 0;
    }

    private int scoreStructure(String prompt, List<String> strengths, List<String> improvements) {
        int score = 0;
        boolean hasLineBreaks = false;
        boolean hasStructureElements = false;

        if (prompt.contains("\n")) {
            score += 8;
            hasLineBreaks = true;
            strengths.add("Well-structured with line breaks");
        } else {
            improvements.add("Use line breaks for better readability");
        }

        // Check for structure elements
        String lowerPrompt = prompt.toLowerCase();

        // Keywords
        if (lowerPrompt.contains("step") || lowerPrompt.contains("bullet") ||
                lowerPrompt.contains("point") || lowerPrompt.contains("list") ||
                lowerPrompt.contains("section") || lowerPrompt.contains("part") ||
                lowerPrompt.contains("chapter") || lowerPrompt.contains("phase") ||
                lowerPrompt.contains("category") || lowerPrompt.contains("element") ||
                lowerPrompt.contains("item")) {
            hasStructureElements = true;
        }

        // Numbered lists (1., 2., etc.)
        if (prompt.matches("(?s).*\\d+\\.\\s+.*")) {
            hasStructureElements = true;
        }

        // Numbered parentheses (1), 2), etc.)
        if (prompt.matches("(?s).*\\d+\\)\\s+.*")) {
            hasStructureElements = true;
        }

        // Bullet points
        if (prompt.contains("- ") || prompt.contains("* ") || prompt.contains("• ") ||
                prompt.contains("– ") || prompt.matches("(?s).*[●○▪▫]\\s+.*")) {
            hasStructureElements = true;
        }

        // Roman numerals (I., II., etc.)
        if (prompt.matches("(?s).*[IVXLCDM]+\\.\\s+.*")) {
            hasStructureElements = true;
        }

        // Letter lists (A., B., etc.)
        if (prompt.matches("(?s).*[A-Z]\\.\\s+.*")) {
            hasStructureElements = true;
        }

        if (hasStructureElements) {
            score += 7;
            strengths.add("Uses structured elements (steps, bullets, lists, sections)");
        } else {
            improvements.add("Add structured elements (steps, bullets, numbered lists, sections)");
        }

        if (hasLineBreaks && hasStructureElements && score >= 15) {
            strengths.add("Excellent structural organization");
        }

        return score;
    }

    private int scoreCompleteness(String prompt, List<String> strengths, List<String> improvements) {
        if (prompt.length() >= 80) {
            strengths.add("Sufficient detail and completeness");
            return 15;
        }
        improvements.add("Add more detail (aim for 80+ characters)");
        return 0;
    }

    private int scoreToolCompatibility(String prompt, String tool, List<String> strengths, List<String> improvements) {
        if (tool == null) {
            return 0;
        }

        String lowerTool = tool.toLowerCase();

        if ("chatgpt".equals(lowerTool) || "gpt".equals(lowerTool) || "openai".equals(lowerTool)) {
            if (prompt.length() > 40) {
                strengths.add("Well-suited for ChatGPT (detailed prompt)");
                return 10;
            } else {
                improvements.add("ChatGPT performs better with detailed prompts (40+ characters)");
                return 0;
            }
        }

        if ("image".equals(lowerTool) || "dalle".equals(lowerTool) || "midjourney".equals(lowerTool) ||
                "stable diffusion".equals(lowerTool) || "imagegen".equals(lowerTool)) {
            if (prompt.matches("(?i).*(image|visual|scene|style|colors|photograph|illustration|painting|drawing|graphic|picture|photo|artwork|rendering|sketch|depict|portray|show|display|visualize|aesthetic|composition|lighting|perspective|angle|view|background|foreground).*")) {
                strengths.add("Includes visual descriptors for image generation");
                return 10;
            } else {
                improvements.add("Add visual descriptors (style, colors, scene, composition, lighting, perspective)");
                return 0;
            }
        }

        return 0;
    }

    private int scoreNoiseAndSafety(String prompt, List<String> strengths, List<String> improvements) {
        if (prompt.matches("(?i).*(lol|pls|stuff|things|whatever|idk|haha|omg|wtf|smh|brb|tbh|imo|imho|afaik|btw|fyi|rofl|lmao|jk|nvm|ugh|meh|duh|sigh|facepalm|\\buh\\b|\\bum\\b|\\ber\\b|\\blike\\b{3,}).*")) {
            improvements.add("Remove casual/informal language (lol, pls, idk, stuff, things, etc.)");
            return 0;
        }

        strengths.add("Professional tone, no noise");
        return 10;
    }

    // =============================
    // GRADE CALCULATION
    // =============================

    String determineGrade(int score) {
        if (score >= 90) return "Excellent";
        if (score >= 75) return "Very Good";
        if (score >= 60) return "Good";
        if (score >= 40) return "Needs Work";
        return "Poor";
    }
}