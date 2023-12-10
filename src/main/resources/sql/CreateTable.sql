CREATE TABLE IF NOT EXISTS public.rule
(
    "time" timestamp with time zone NOT NULL,
    ruleId varchar(255),
    policyId varchar(255),
    "data" jsonb,
    md5 varchar(255)
    );

SELECT create_hypertable('rule', 'time', chunk_time_interval => INTERVAL '1 day');