INSERT INTO `franchise` (FRANCHISE_ID, NAME, CREATE_AT, UPDATE_AT)
    VALUES (1, 'GS25', NOW(), NOW()),
           (2, 'CU', NOW(), NOW()),
           (3, 'SEVEN_ELEVEN', NOW(), NOW());

INSERT INTO `item` (ITEM_ID, CREATE_AT, UPDATE_AT, IMAGE_URL, ITEM_NAME, PRICE, FRANCHISE_ID)
    VALUES ( 1, NOW(), NOW(), 'images/item_1.png', '삼각김밥' , 1600, 1),
           ( 2, NOW(), NOW(), 'images/item_2.png', '삼각김밥2' , 1600, 1),
           ( 3, NOW(), NOW(), 'images/item_3.png', '삼각김밥3' , 1600, 1),
           ( 4, NOW(), NOW(), 'images/item_4.png', '삼각김밥4' , 1600, 2),
           ( 5, NOW(), NOW(), 'images/item_5.png', '삼각김밥5' , 1600, 2),
           ( 6, NOW(), NOW(), 'images/item_6.png', '삼각김밥6' , 1600, 2),
           ( 7, NOW(), NOW(), 'images/item_7.png', '삼각김밥7' , 1600, 3),
           ( 8, NOW(), NOW(), 'images/item_8.png', '삼각김밥8' , 1600, 3),
           ( 9, NOW(), NOW(), 'images/item_9.png', '삼각김밥9' , 1600, 3),
           ( 10, NOW(), NOW(), 'images/item_10.png', '삼각김밥10' , 1600, 3);

INSERT INTO `discount` (DISCOUNT_ID, CREATE_AT, DISCOUNT_TYPE, UPDATE_AT, END_DATE, START_DATE, ITEM_ID)
    VALUES (1, NOW(), 'ONEPLUS', NOW(), DATEADD(DAY, 10, NOW()),  NOW(), 2),
           (2, NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()),  NOW(), 4),
           (3, NOW(), 'TWOPLUS', NOW(), DATEADD(DAY, 10, NOW()),  NOW(), 6);
