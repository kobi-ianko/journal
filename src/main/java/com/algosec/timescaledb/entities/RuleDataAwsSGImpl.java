package com.algosec.timescaledb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDataAwsSGImpl implements RuleData{

    private String ruleType = "RuleDataAwsSGImpl";
    
    private String ruleNum;
    private String ruleId;
    private String source;
    private String destination;
    private String service;
    private String action;

    private String direction;
    private String name;
    private String priority;
    private String description;
    private String ruleGroup;
    private String type;

    private String protocol;
    private String portRange;
    
}
