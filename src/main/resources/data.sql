INSERT INTO `franchise` (NAME, CREATE_AT, UPDATE_AT)
    VALUES ( 'GS25', NOW(), NOW()),
           ( 'CU', NOW(), NOW()),
           ( 'SEVEN_ELEVEN', NOW(), NOW());

INSERT INTO `item` (CREATE_AT, UPDATE_AT, IMAGE_URL, ITEM_NAME, PRICE, FRANCHISE_ID)
    VALUES (  NOW(), NOW(), 'images/item_1.png', '삼각김밥' , 1600, 1),
           (  NOW(), NOW(), 'images/item_2.png', '삼각김밥2' , 1600, 1),
           (  NOW(), NOW(), 'images/item_3.png', '삼각김밥3' , 1600, 1),
           (  NOW(), NOW(), 'images/item_4.png', '삼각김밥4' , 1600, 2),
           (  NOW(), NOW(), 'images/item_5.png', '삼각김밥5' , 1600, 2),
           (  NOW(), NOW(), 'images/item_6.png', '삼각김밥6' , 1600, 2),
           (  NOW(), NOW(), 'images/item_7.png', '삼각김밥7' , 1600, 3),
           (  NOW(), NOW(), 'images/item_8.png', '삼각김밥8' , 1600, 3),
           (  NOW(), NOW(), 'images/item_9.png', '삼각김밥9' , 1600, 3),
           ( NOW(), NOW(), 'images/item_10.png', '삼각김밥10' , 1600, 3);

INSERT INTO `discount` (CREATE_AT, DISCOUNT_TYPE, UPDATE_AT, END_DATE, START_DATE, ITEM_ID)
    VALUES ( NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()),  NOW(), 2),
           ( NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()),  NOW(), 4),
           (NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()),  NOW(), 6);


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

