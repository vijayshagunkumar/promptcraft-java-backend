package com.promptcraft.gst.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promptcraft.gst.engine.GstDecisionInput;
import com.promptcraft.gst.engine.GstRuleEvaluationResult;
import com.promptcraft.gst.engine.RuleEvaluator;
import com.promptcraft.gst.entity.GstEvaluationAudit;
import com.promptcraft.gst.loader.GstRuleLoader;
import com.promptcraft.gst.model.GstRuleSet;
import com.promptcraft.gst.persistence.GstEvaluationAuditRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/gst")
public class GstEvaluationController {

    private final GstRuleLoader ruleLoader;
    private final RuleEvaluator evaluator;
    private final GstEvaluationAuditRepository auditRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GstEvaluationController(
            GstRuleLoader ruleLoader,
            RuleEvaluator evaluator,
            GstEvaluationAuditRepository auditRepository
    ) {
        this.ruleLoader = ruleLoader;
        this.evaluator = evaluator;
        this.auditRepository = auditRepository;
    }

    @PostMapping("/evaluate")
    public GstEvaluationResponse evaluate(
            @RequestBody GstEvaluationRequest request
    ) {

        // 1️⃣ Load rules
        GstRuleSet ruleSet =
                ruleLoader.loadExemptionRules(request.getVersion());

        // 2️⃣ Build decision input
        GstDecisionInput input =
                new GstDecisionInput(request.getFacts());

        // 3️⃣ Evaluate rules
        List<GstRuleEvaluationResult> results =
                evaluator.evaluate(ruleSet, input);

        // 4️⃣ Persist audit (NON-BLOCKING)
        try {
            GstEvaluationAudit audit = new GstEvaluationAudit();
            audit.setRuleVersion(request.getVersion());
            audit.setMatchedRules(results.size());
            audit.setEvaluatedAt(Instant.now());
            audit.setRequestFacts(
                    objectMapper.writeValueAsString(request.getFacts())
            );
            audit.setEvaluationResult(
                    objectMapper.writeValueAsString(results)
            );

            auditRepository.save(audit);
        } catch (Exception ignored) {
            // audit failure must NEVER break main evaluation
        }

        // 5️⃣ Build response
        GstEvaluationResponse response = new GstEvaluationResponse();
        response.setRuleVersion(request.getVersion());
        response.setMatchedRules(results.size());
        response.setResults(results);

        return response;
    }
}
