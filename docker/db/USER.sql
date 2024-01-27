CREATE TABLE PUBLIC.USER (
    ID SERIAL PRIMARY KEY, -- первичный ключ
    ROLE_ID INTEGER NOT NULL, -- внешний ключ к таблице ролей
    NAME VARCHAR(128) NOT NULL, -- имя гостя
    BIRTHDAY DATE NOT NULL, -- дата рождения гостя
    PASSWORD TEXT NOT NULL,
    EMAIL VARCHAR(320) NOT NULL UNIQUE, -- имеил
    PHONE INTEGER NOT NULL UNIQUE, -- телефон
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_ROLES
        FOREIGN KEY (ROLE_ID)
            REFERENCES PUBLIC.ROLES(ID)
);

drop table public.user cascade ;