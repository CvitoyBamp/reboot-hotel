CREATE TABLE PUBLIC.ROOM_TYPE (
    ID SERIAL PRIMARY KEY, -- первичный ключ шаблона
    ROOM_NAME VARCHAR(64) NOT NULL, -- название номера (Single Room, Family Room и пр.)
    DESCRIPTION TEXT NOT NULL, -- описание номера
    MAX_ADULTS SMALLINT NOT NULL,
    MAX_CHILDREN SMALLINT NOT NULL,
    ADDS JSONB, -- доп. удобства в номере {"tv": true, "wifi": true, "fridge": true, "conditioner": true}
    PHOTO_ID INTEGER NOT NULL, -- внешний ключ на таблицу с фотографиями номера
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_PHOTO_ID
        FOREIGN KEY (PHOTO_ID)
            REFERENCES PUBLIC.PHOTO_STORE(ID)
);

drop table PUBLIC.ROOM_TYPE cascade;