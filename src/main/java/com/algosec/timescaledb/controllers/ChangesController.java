package com.algosec.timescaledb.controllers;

import com.algosec.timescaledb.entities.Rule;
import com.algosec.timescaledb.services.ChangesGenarationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("rule-changes")
@RequiredArgsConstructor
public class ChangesController {

    final private ChangesGenarationService changesGenarationService;
    
    @GetMapping("ping")
    public String ping() {
        return "Pong";
    }

    @PostMapping("create")
    public void create() {
        changesGenarationService.CreateChange();
    }

    @GetMapping("")
    public List<Rule> find() {
        return changesGenarationService.getAllRules();
    }

    @GetMapping("/rule/{ruleId}")
    public Rule find(@PathVariable("ruleId") String ruleId) {
        return changesGenarationService.getLatest(ruleId);
    }

    @GetMapping("/rule/month/{ruleId}")
    public List<Rule> findMonthly(@PathVariable("ruleId") String ruleId) {
        return changesGenarationService.getMonthly(ruleId);
    }

    @GetMapping("/rule/year/{ruleId}")
    public List<Rule> findYearly(@PathVariable("ruleId") String ruleId) {
        return changesGenarationService.getYearly(ruleId);
    }
    

}
