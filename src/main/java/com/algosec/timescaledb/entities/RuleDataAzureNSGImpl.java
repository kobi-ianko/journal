package com.algosec.timescaledb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDataAzureNSGImpl implements RuleData{

    private String ruleType = "RuleDataAzureNSGImpl";
    
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
    
    
    
    

}
