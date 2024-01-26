CREATE TABLE PUBLIC.USER (
    ID SERIAL PRIMARY KEY, -- первичный ключ
    NAME VARCHAR(128) NOT NULL, -- имя гостя
    BIRTHDAY DATE NOT NULL, -- дата рождения гостя
    EMAIL VARCHAR(320) NOT NULL UNIQUE, -- имеил
    PHONE INTEGER NOT NULL UNIQUE, -- телефон
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- будет меняться при апдейтах за счет триггеров
);

drop table public.user cascade ;