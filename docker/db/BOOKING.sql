CREATE TABLE PUBLIC.BOOKING (
    ORDER_NUMBER SERIAL PRIMARY KEY, -- номер заказа (первичный ключ)
    USER_ID INTEGER, -- внешний ключ из таблицы юзер
    ROOM_ID INTEGER, -- внешний ключ из таблицы номер
    START_DATE DATE NOT NULL, -- заезд
    END_DATE DATE NOT NULL, -- выезд
    CONSTRAINT FK_USER
        FOREIGN KEY (USER_ID)
            REFERENCES PUBLIC.USER(ID),
    CONSTRAINT FK_ROOM
        FOREIGN KEY (ROOM_ID)
            REFERENCES PUBLIC.ROOM(ID)
);

drop table booking;