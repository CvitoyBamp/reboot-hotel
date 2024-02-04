CREATE TABLE PUBLIC.BOOKING (
    ID SERIAL PRIMARY KEY, -- номер заказа (первичный ключ)
    USER_ID INTEGER, -- внешний ключ из таблицы юзер
    ROOM_ID INTEGER, -- внешний ключ из таблицы номер
    START_DATE DATE NOT NULL, -- заезд
    END_DATE DATE NOT NULL, -- выезд
    NOTE TEXT,
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_BOOKING_USER
        FOREIGN KEY (USER_ID)
            REFERENCES PUBLIC.USER(ID),
    CONSTRAINT FK_BOOKING_ROOM
        FOREIGN KEY (ROOM_ID)
            REFERENCES PUBLIC.ROOM(ID)
);

drop table booking cascade;