-- 음식종류별로 즐겨찾기수가 가장 많은 식당의 음식 종류, ID, 식당 이름, 즐겨찾기수
-- 음식 종류를 기준으로 내림차순 정렬 

-- 음식 종류별 group by 후 favorites 가장 많은 식당 고르기 
-- 서브쿼리로 음식 종류별 favorites 가장 많은 식당의 id와 음식 종류 조회 

SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES FROM REST_INFO
WHERE (FOOD_TYPE, FAVORITES) IN 
(
    SELECT FOOD_TYPE, MAX(FAVORITES) FAVORITES FROM REST_INFO
    GROUP BY FOOD_TYPE
)
ORDER BY FOOD_TYPE DESC;