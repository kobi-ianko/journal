package com.algosec.timescaledb.entities;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.usertype.UserType;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "rule")
@IdClass(RuleId.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rule {

    @Id
    private LocalDate time;
    
    @Id
    @Column(name = "ruleid")
    private String ruleId;
    
    @Column(name = "policyid")
    private String policyId;
    
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private RuleData data;
    
    @Column
    private String md5;  

}
