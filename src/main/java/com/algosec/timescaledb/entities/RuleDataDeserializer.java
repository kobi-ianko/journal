package com.algosec.timescaledb.entities;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class RuleDataDeserializer extends StdDeserializer<RuleData> {

    public RuleDataDeserializer() {
        this(null);
    }

    public RuleDataDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RuleData deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        String ruleType = (node.get("ruleType")).asText();
        switch (ruleType) {
            case "RuleDataAzureNSGImpl":
                return new RuleDataAzureNSGImpl(ruleType, node.get("ruleNum").asText(),
                    node.get("ruleId").asText(), node.get("source").asText(),
                    node.get("destination").asText(), node.get("service").asText(),
                    node.get("action").asText(), node.get("direction").asText(),
                    node.get("name").asText(), node.get("priority").asText(),
                    node.get("description").asText(), node.get("ruleGroup").asText(),
                    node.get("type").asText());
            case "RuleDataAwsSGImpl":
                return new RuleDataAwsSGImpl(ruleType, node.get("ruleNum").asText(),
                    node.get("ruleId").asText(), node.get("source").asText(),
                    node.get("destination").asText(), node.get("service").asText(),
                    node.get("action").asText(), node.get("direction").asText(),
                    node.get("name").asText(), node.get("priority").asText(),
                    node.get("description").asText(), node.get("ruleGroup").asText(),
                    node.get("type").asText(),node.get("protocol").asText(),node.get("portRange").asText());
                    
            default:
                return null;
        }
        
    }

}