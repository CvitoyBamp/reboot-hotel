CREATE TABLE PUBLIC.ROLES (
    ID SERIAL PRIMARY KEY, -- первичный ключ роли
    ROLE_NAME VARCHAR(64) NOT NULL CHECK (ROLE_NAME in ('ADMIN','USER')), -- имя роли
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- будет меняться при апдейтах за счет триггеров
);

drop table PUBLIC.ROLES cascade;