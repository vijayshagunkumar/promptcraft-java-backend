package com.promptcraft.gst.engine;

import org.springframework.stereotype.Component;

import com.promptcraft.gst.model.GstRule;
import com.promptcraft.gst.model.GstRuleSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RuleEvaluator {

    public List<GstRuleEvaluationResult> evaluate(
            GstRuleSet ruleSet,
            GstDecisionInput input
    ) {

        List<GstRuleEvaluationResult> results = new ArrayList<>();

        for (GstRule rule : ruleSet.getRules()) {

            if (!applies(rule.getAppliesWhen(), input)) {
                continue;
            }

            GstRuleEvaluationResult result = new GstRuleEvaluationResult();
            result.setRuleId(rule.getRuleId());
            result.setDecisionType(rule.getDecisionType());
            result.setPriority(rule.getPriority());

            Map<String, Object> risk = rule.getRiskIfTriggered();
            if (risk != null) {
                result.setRiskLevel((String) risk.get("risk_level"));
                result.setRiskReason((String) risk.get("reason"));
                result.setLawReference(
                        (Map<String, Object>) risk.get("law_reference")
                );
            }

            result.setSecondOrderEffects(rule.getSecondOrderEffects());
            result.setForesightTags(rule.getForesightTags());

            results.add(result);
        }

        return results;
    }

    private boolean applies(Map<String, Object> conditions, GstDecisionInput input) {

        if (conditions == null || conditions.isEmpty()) {
            return true;
        }

        for (Map.Entry<String, Object> condition : conditions.entrySet()) {
            Object factValue = input.get(condition.getKey());
            if (factValue == null || !factValue.equals(condition.getValue())) {
                return false;
            }
        }
        return true;
    }
}
