package com.promptcraft.gst.engine;

import com.promptcraft.gst.loader.GstRuleLoader;
import com.promptcraft.gst.model.GstRuleSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleEvaluatorTest {

    @Test
    void shouldTriggerSezExemptionRule() {

        // Load rules
        GstRuleSet ruleSet = new GstRuleLoader()
                .loadExemptionRules("v1.0.0");

        // Create decision input
        GstDecisionInput input = new GstDecisionInput();
        input.put("supply_to", "SEZ_UNIT_OR_DEVELOPER");
        input.put("exemption_claimed", true);
        input.put("supply_purpose", "AUTHORIZED_OPERATIONS");

        // Evaluate
        RuleEvaluator evaluator = new RuleEvaluator();
        List<GstRuleEvaluationResult> results =
                evaluator.evaluate(ruleSet, input);

        // Assertions
        assertFalse(results.isEmpty(), "Expected at least one rule to trigger");

        GstRuleEvaluationResult result = results.get(0);
        assertEquals("EXEMPT-SEZ-001", result.getRuleId());
        assertEquals("HIGH", result.getRiskLevel());
    }
}
