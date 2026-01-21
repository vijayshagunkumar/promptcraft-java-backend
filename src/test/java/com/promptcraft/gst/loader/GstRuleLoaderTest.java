package com.promptcraft.gst.loader;

import com.promptcraft.gst.model.GstRuleSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GstRuleLoaderTest {

    @Autowired
    private GstRuleLoader gstRuleLoader;

    @Test
    void shouldLoadExemptionRules() {
        GstRuleSet ruleSet = gstRuleLoader.loadExemptionRules("v1.0.0");

        assertThat(ruleSet).isNotNull();
        assertThat(ruleSet.getRules()).isNotEmpty();

    }
}
