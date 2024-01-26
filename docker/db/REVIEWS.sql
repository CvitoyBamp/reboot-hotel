CREATE TABLE PUBLIC.REVIEWS (
    REVIEW_NUMBER SERIAL PRIMARY KEY, -- номер отзыва (первичный ключ)
    USER_ID INTEGER NOT NULL, -- внешний ключ из таблицы юзер
    COMMENT TEXT NOT NULL, -- комментарий клиента
    RATING SMALLINT CHECK (RATING BETWEEN 1 AND 5), -- оценка с констрейнтом от 1 до 5
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_REVIEWS_USER
        FOREIGN KEY (USER_ID)
            REFERENCES PUBLIC.USER(ID)
);

drop table booking;