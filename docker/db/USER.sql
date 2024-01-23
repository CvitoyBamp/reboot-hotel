CREATE TABLE PUBLIC.USER (
    ID SERIAL PRIMARY KEY, -- первичный ключ
    NAME VARCHAR(128) NOT NULL, -- имя гостя
    BIRTHDAY DATE NOT NULL, -- дата рождения гостя
    EMAIL VARCHAR(320) NOT NULL UNIQUE, -- имеил
    PHONE INTEGER NOT NULL UNIQUE -- телефон
);

drop table public.user cascade ;