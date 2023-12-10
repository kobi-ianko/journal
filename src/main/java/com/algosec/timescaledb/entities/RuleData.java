package com.algosec.timescaledb.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = RuleDataDeserializer.class)
public interface RuleData {
    
}
