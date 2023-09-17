DROP TABLE IF EXISTS PRICES;

-- PRICES table
CREATE TABLE PRICES
(
    ID         INT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID   INT              NOT NULL,
    START_DATE TIMESTAMP        NOT NULL,
    END_DATE   TIMESTAMP        NOT NULL,
    PRICE_LIST BIGINT           NOT NULL,
    PRODUCT_ID BIGINT           NOT NULL,
    PRIORITY   INT              NOT NULL,
    PRICE      DOUBLE PRECISION NOT NULL,
    CURR       VARCHAR(3)       NOT NULL
);

-- Data
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR)
VALUES (1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 35455, 0, 35.5, 'EUR'),
       (1, '2020-06-14T06:00:00', '2020-06-14T18:30:00', 2, 35455, 1, 25.45, 'EUR'),
       (1, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 3, 35455, 1, 30.5, 'EUR'),
       (1, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 4, 35455, 1, 38.95, 'EUR')
;