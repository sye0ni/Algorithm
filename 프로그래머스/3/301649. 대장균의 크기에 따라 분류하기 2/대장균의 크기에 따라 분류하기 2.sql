SELECT ID,
CASE
when PERCENT_rank() over (ORDER by size_of_colony desc) <= 0.25 then 'CRITICAL'
WHEN PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) <= 0.5 THEN 'HIGH'
WHEN PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) <= 0.75 THEN 'MEDIUM'
ELSE 'LOW'
END
AS COLONY_NAME
FROM ECOLI_DATA
ORDER BY ID