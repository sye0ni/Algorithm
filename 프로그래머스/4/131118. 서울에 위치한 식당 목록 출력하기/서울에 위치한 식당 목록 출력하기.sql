-- 서울에 위치한 식당들의 식당 ID, 식당 이름, 음식 종류, 즐겨찾기수, 주소, 리뷰 평균 점수(세번째자리반올림)를 조회 
-- 평균 점수 내림차순, 즐겨찾기 내림차순 

SELECT INFO.REST_ID, INFO.REST_NAME, INFO.FOOD_TYPE, INFO.FAVORITES, 
INFO.ADDRESS, ROUND(AVG(REVIEW.REVIEW_SCORE),2) AS SCORE
FROM REST_INFO INFO JOIN REST_REVIEW REVIEW 
ON INFO.REST_ID=REVIEW.REST_ID 
WHERE INFO.ADDRESS LIKE '서울%'
GROUP BY REVIEW.REST_ID
ORDER BY SCORE DESC, FAVORITES DESC;