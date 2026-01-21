package com.promptcraft.gst.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promptcraft.gst.model.GstRuleSet;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class GstRuleLoader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GstRuleSet loadExemptionRules(String version) {
        String path = String.format(
                "rules/gst/exemptions/%s/rules.json",
                version
        );

        try (InputStream is =
                     getClass().getClassLoader().getResourceAsStream(path)) {

            if (is == null) {
                throw new IllegalStateException(
                        "GST rules not found at path: " + path
                );
            }

            return objectMapper.readValue(is, GstRuleSet.class);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to load GST rules for version: " + version, e
            );
        }
    }
}
