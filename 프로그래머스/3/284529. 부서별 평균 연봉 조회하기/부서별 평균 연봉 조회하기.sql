SELECT EMP.DEPT_ID,DEPT.DEPT_NAME_EN AS DEPT_NAME_EN, ROUND(AVG(SAL)) AS AVG_SAL 
FROM HR_EMPLOYEES AS EMP
JOIN HR_DEPARTMENT AS DEPT
ON EMP.DEPT_ID=DEPT.DEPT_ID
GROUP BY DEPT_ID 
ORDER BY AVG_SAL DESC;