package com.algosec.timescaledb.services;

import com.algosec.timescaledb.entities.Rule;
import com.algosec.timescaledb.entities.RuleData;
import com.algosec.timescaledb.entities.RuleDataAwsSGImpl;
import com.algosec.timescaledb.entities.RuleDataAzureImpl;
import com.algosec.timescaledb.entities.RuleDataAzureNSGImpl;
import com.algosec.timescaledb.entities.RuleDataGcpImpl;
import com.algosec.timescaledb.repositories.RuleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ChangesGenarationService {

    private final RuleRepository ruleRepository;
    
    @Transactional
    public void CreateChange() {
        Random random = new Random();
        
        for (int i=10000; i>0; i--) {

            String number = String.valueOf(random.nextInt(100));
            RuleData data1 = RuleDataAzureNSGImpl.builder().ruleNum(number).ruleId("36c946f92b893bc801b85c2169c61729"+number).source("VirtualNetwork"+String.valueOf(random.nextInt(100))).destination("VirtualNetwork"+String.valueOf(random.nextInt(100))).service("Any").action("Allow").direction("Inbound").name("AllowVnetInBound").priority("65000").description(":Allow inbound traffic from all VMs in VNET").ruleGroup("IT-Test-nsg").type("Subnet Network Security Group").build();
            ((RuleDataAzureNSGImpl)data1).setRuleType("RuleDataAzureNSGImpl");

            String md5Hex = DigestUtils
                .md5DigestAsHex(data1.toString().getBytes(StandardCharsets.UTF_8)).toUpperCase();
                
            Rule r1 = Rule.builder().time(LocalDate.now().minusDays(i)).ruleId("36c946f92b893bc801b85c2169c61729"+number).md5(md5Hex).policyId("policy1235AzureNSG").data(data1).build();

            number = String.valueOf(random.nextInt(100));
            RuleData data2 = RuleDataAwsSGImpl.builder().ruleNum(number).ruleId("36c946f92b893bc801b85c2169c61729"+number).source("VirtualNetwork"+String.valueOf(random.nextInt(100))).destination("VirtualNetwork"+String.valueOf(random.nextInt(100))).service("Any").action("Allow").direction("Inbound").name("AllowVnetInBound").priority("65000").description(":Allow inbound traffic from all VMs in VNET").ruleGroup("IT-Test-nsg").type("Subnet Network Security Group").protocol("tcp").portRange("1-22").build();
            ((RuleDataAwsSGImpl)data2).setRuleType("RuleDataAwsSGImpl");

            md5Hex = DigestUtils
                .md5DigestAsHex(data2.toString().getBytes(StandardCharsets.UTF_8)).toUpperCase();
                
            Rule r2 = Rule.builder().time(LocalDate.now().minusDays(i)).ruleId("36c946f92b893bc801b85c2169c61729"+number).md5(md5Hex).policyId("policy1235AwsSG").data(data2).build();

            ruleRepository.save(r1);
            ruleRepository.save(r2);
        }
        
    }
    
    public List<Rule> getAllRules() {
        List<Rule> rules = ruleRepository.findAll();
        return rules;
    }
    
//    public Rule getLatest(String ruleId) {
//        return ruleRepository.findFirstByRuleIdOrderByTimeDesc(ruleId);
//    }

    public Rule getLatest(String ruleId) {
        return ruleRepository.findLatestByRuleId(ruleId);
    }

    public List<Rule> getYearly(String ruleId) {
        return ruleRepository.findYearlyByRuleId(ruleId);
    }

    public List<Rule> getMonthly(String ruleId) {
        return ruleRepository.findMonthlyByRuleId(ruleId);
    }

}
