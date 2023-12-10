package com.algosec.timescaledb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleDataGcpImpl implements RuleData{

    private String ruleType = this.getClass().getName();
    
    private String param1;
    private String param2;
    private String param3;
    private String param4;

}
