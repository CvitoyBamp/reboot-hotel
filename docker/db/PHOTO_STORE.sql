CREATE TABLE PUBLIC.PHOTO_STORE (
    ID SERIAL PRIMARY KEY, -- первичный ключ шаблона
    PHOTO BYTEA NOT NULL UNIQUE, -- фото в бинарном формате
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- будет меняться при апдейтах за счет триггеров
);