INSERT INTO `franchise` (NAME, CREATE_AT, UPDATE_AT)
    VALUES ( 'GS25', NOW(), NOW()),
           ( 'CU', NOW(), NOW()),
           ( 'SEVEN_ELEVEN', NOW(), NOW());

INSERT INTO `item` (CREATE_AT, UPDATE_AT, IMAGE_URL, ITEM_NAME, PRICE, FRANCHISE_ID)
VALUES
    (NOW(), NOW(), 'images/img.png', '농심 신라면투움바큰사발', 2000, 1),
    (NOW(), NOW(), 'images/img_1.png', '코카 스프라이트PET500ML', 2200, 1),
    (NOW(), NOW(), 'images/img_2.png', 'CJ 누룽지닭백숙죽', 5500, 1),
    (NOW(), NOW(), 'images/img_3.png', 'CJ 닭가슴살소시지청양80g', 2900, 1),
    (NOW(), NOW(), 'images/img_4.png', 'HK 새싹보리p500ml', 2500, 1),
    (NOW(), NOW(), 'images/img_5.png', '광동 비타500D병100ml', 1000, 1),
    (NOW(), NOW(), 'images/img_6.png', 'SA 하츄핑비스킷65g', 1700, 1),
    (NOW(), NOW(), 'images/img_7.png', 'CJ 맥스봉직화구이꼬치바90g', 2700, 1),
    (NOW(), NOW(), 'images/img_8.png', '오뚜기 컵밥김치참치덮밥', 0, 1),
    (NOW(), NOW(), 'images/img_9.png', '바프 와사비맛아몬드40g', 2500, 1),
    (NOW(), NOW(), 'images/img_10.png', 'CJ BIG스팸마요덮밥(컵밥)', 5400, 2),
    (NOW(), NOW(), 'images/img_11.png', 'CJ 비비고군만두315G', 7800, 2),
    (NOW(), NOW(), 'images/img_12.png', '오뚜기 진짬뽕큰컵115g', 1800, 2),
    (NOW(), NOW(), 'images/img_13.png', '롯데 비빔참치마요삼각김밥100g', 1200, 2),
    (NOW(), NOW(), 'images/img_14.png', '동서 레드불캔250ml', 1600, 2),
    (NOW(), NOW(), 'images/img_15.png', 'CJ 고소치즈후랑크65g', 2400, 2),
    (NOW(), NOW(), 'images/img_16.png', 'CJ 맛밤42G', 2000, 2),
    (NOW(), NOW(), 'images/img_17.png', '덴마크 얼라이브블러드오렌지250', 1700, 2),
    (NOW(), NOW(), 'images/img_18.png', '농심 카구리큰사발(대컵)', 1200, 2),
    (NOW(), NOW(), 'images/img_19.png', 'HK 납작복숭아아이스티500ML', 2500, 2),
    (NOW(), NOW(), 'images/img_20.png', 'CJ 닭가슴살소시지80g', 2900, 3),
    (NOW(), NOW(), 'images/img_21.png', 'MQ 프링글스매운맛53g', 2000, 3),
    (NOW(), NOW(), 'images/img_22.png', 'MZ 밀카밀크초코렛100g', 4000, 3),
    (NOW(), NOW(), 'images/img_23.png', '마즈 이클립스플러스꿀&레몬향', 3500, 3),
    (NOW(), NOW(), 'images/img_24.png', '농심 누들핏짜파구리맛', 1800, 3),
    (NOW(), NOW(), 'images/img_25.png', 'CJ BIG치즈닭갈비덮밥(컵밥)', 5400, 3),
    (NOW(), NOW(), 'images/img_26.png', '가야 알로에농장500ML', 2350, 3),
    (NOW(), NOW(), 'images/img_27.png', '남양 초콜릿드링크초코에몽250ML', 1900, 3),
    (NOW(), NOW(), 'images/img_28.png', '롯데 꼬깔콘고소한맛(봉지)', 1700, 3),
    (NOW(), NOW(), 'images/img_29.png', 'CJ 강된장보리비빔밥(컵반)', 4800, 3);


INSERT INTO `discount` (CREATE_AT, DISCOUNT_TYPE, UPDATE_AT, END_DATE, START_DATE, ITEM_ID)
VALUES
    -- CU 1+1
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 1),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 2),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 3),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 4),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 5),

    -- CU 2+1
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 6),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 7),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 8),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 9),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 10),

    -- GS25 2+1
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 15),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 16),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 17),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 18),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 19),

    -- 세븐일레븐 1+1
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 20),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 21),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 22),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 23),
    (NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 24),

    -- 세븐일레븐 2+1
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 25),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 26),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 27),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 28),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 29),
    (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()), NOW(), 30);



INSERT INTO `combination` (CREATE_AT, UPDATE_AT, CATEGORY, `DESCRIPTION`, LIKE_COUNT, TITLE, FRANCHISE_ID)
VALUES ( NOW(), NOW(), 'LOW_PRICE', '엄청 맛잇다', 1, '삼김조1', 1),
       ( NOW(), NOW(), 'LOW_PRICE', '엄청 맛잇다', 1, '삼김조2', 2),
       ( NOW(), NOW(), 'LOW_PRICE', '엄청 맛잇다', 1, '삼김조3', 3),
       ( NOW(), NOW(), 'HOT_CHALLENGE', '죤내 맵다다', 1, '삼김조4', 3);

INSERT INTO `combination_item` ( CREATE_AT, UPDATE_AT, COMBINATION_ID, FRANCHISE_ID, ITEM_ID)
VALUES ( NOW(), NOW(), 1, 1, 1),
       ( NOW(), NOW(), 1, 1, 2),
       ( NOW(), NOW(), 1, 1, 3),
       ( NOW(), NOW(), 2, 2, 4),
       ( NOW(), NOW(), 2, 2, 5),
       ( NOW(), NOW(), 2, 2, 6),
       ( NOW(), NOW(), 3, 3, 7),
       ( NOW(), NOW(), 3, 3, 8),
       ( NOW(), NOW(), 4, 3, 9),
       ( NOW(), NOW(), 4, 3, 10);

