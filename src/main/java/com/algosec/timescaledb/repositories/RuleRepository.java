package com.algosec.timescaledb.repositories;

import com.algosec.timescaledb.entities.Rule;
import com.algosec.timescaledb.entities.RuleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, RuleId> {
    
    Rule findFirstByRuleIdOrderByTimeDesc(String ruleId);


    @Query(value = "SELECT ruleid, LAST(time, time) AS time, time_bucket('1 day', time) AS times, LAST(data, time) AS data, LAST(md5, time) AS md5, LAST(policyId, time) AS policyId " +
    "FROM rule " +
    "WHERE ruleId = :ruleId " +
    "and time > now () - INTERVAL '3 year' " +
    "group by ruleId, times, policyId " +
    "order by times desc " +
    "LIMIT 1", nativeQuery = true)
    Rule findLatestByRuleId(@Param("ruleId") String ruleId);


    @Query(value = "SELECT ruleid, LAST(time, time) AS time, time_bucket('1 year', time) AS times, LAST(data, time) AS data, LAST(md5, time) AS md5, LAST(policyId, time) AS policyId " +
        "FROM rule " +
        "WHERE ruleId = :ruleId " +
        "and time > now () - INTERVAL '3 year' " +
        "group by ruleId, times, policyId " +
        "order by times desc ", nativeQuery = true)
    List<Rule> findYearlyByRuleId(@Param("ruleId") String ruleId);


    @Query(value = "SELECT ruleid, LAST(time, time) AS time, time_bucket('1 month', time) AS times, LAST(data, time) AS data, LAST(md5, time) AS md5, LAST(policyId, time) AS policyId " +
        "FROM rule " +
        "WHERE ruleId = :ruleId " +
        "and time > now () - INTERVAL '3 year' " +
        "group by ruleId, times, policyId " +
        "order by times desc ", nativeQuery = true)
    List<Rule> findMonthlyByRuleId(@Param("ruleId") String ruleId);

}
